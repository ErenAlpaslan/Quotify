package com.easylife.quotify.domain.usecase

import android.util.Log
import com.easylife.quotify.base.BaseUseCase
import com.easylife.quotify.data.mapper.QuoteListDataMapper
import com.easylife.quotify.data.models.QuoteListData
import com.easylife.quotify.domain.repository.LocalQuoteRepository
import com.easylife.quotify.domain.repository.RemoteQuoteRepository
import com.easylife.quotify.utils.QuotifyResult
import com.easylife.quotify.utils.dispatchers.QuotifyDispatchers
import com.easylife.quotify.utils.integration.config.RemoteConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetQuoteListDataUseCase(
    private val localQuoteRepository: LocalQuoteRepository,
    private val remoteQuoteRepository: RemoteQuoteRepository,
    private val dispatchers: QuotifyDispatchers,
    private val remoteConfig: RemoteConfig,
    private val quoteListDataMapper: QuoteListDataMapper
) : BaseUseCase<List<QuoteListData>, GetQuoteListDataUseCase.Param>() {

    data class Param(
        val page: Int,
        val lastSeenIndex: Int,
        val category: String
    )

    override suspend fun execute(params: Param?): Flow<QuotifyResult<List<QuoteListData>>> = flow {
        params?.let {
            val unShownQuotes =
                localQuoteRepository.getUnShownQuotes().filter { it.Category == params.category }
            Log.d("QuotesControl", "UNSHOWN SIZE => ${unShownQuotes.size}")

            if (unShownQuotes.isNotEmpty() && unShownQuotes.size > RemoteConfig.QUOTE_ROW_COUNT / 2) {
                emit(QuotifyResult.Success(quoteListDataMapper.transform(unShownQuotes)))
            } else {
                remoteQuoteRepository.getQuotesWithCategoryByPage(
                    "",
                    params.page
                ).collect { remoteResult ->
                    when (remoteResult) {
                        is QuotifyResult.Error -> emit(
                            QuotifyResult.Error<List<QuoteListData>>(
                                remoteResult.message
                            )
                        )
                        is QuotifyResult.Success -> {
                            remoteResult.data?.let {
                                val quotes = unShownQuotes + remoteResult.data
                                localQuoteRepository.insertAllQuote(remoteResult.data)
                                emit(QuotifyResult.Success(quoteListDataMapper.transform(quotes)))
                            }
                                ?: emit(QuotifyResult.Error<List<QuoteListData>>("Empty quote list try again later"))
                        }
                    }
                }
            }
        }
    }.flowOn(dispatchers.io)

}