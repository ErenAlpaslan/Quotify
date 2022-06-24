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
        val page: Int
    )

    override suspend fun execute(params: Param?): Flow<QuotifyResult<List<QuoteListData>>> = flow {
        params?.let {
            Log.d(TAG, "=> executing")
            remoteQuoteRepository.getQuotesWithCategoryByPage(
                "",
                params.page,
                RemoteConfig.QUOTE_ROW_COUNT
            ).collect { remoteResult ->
                Log.d(TAG, "remote: $remoteResult")

                when (remoteResult) {
                    is QuotifyResult.Error -> emit(
                        QuotifyResult.Error<List<QuoteListData>>(
                            remoteResult.message
                        )
                    )
                    is QuotifyResult.Success -> {
                        remoteResult.data?.let {
                            localQuoteRepository.insertAllQuote(remoteResult.data)
                            emit(QuotifyResult.Success(quoteListDataMapper.transform(remoteResult.data)))
                        } ?: emit(QuotifyResult.Error<List<QuoteListData>>("Empty quote list try again later"))
                    }
                }
            }
        }
    }.flowOn(dispatchers.io)


    companion object {
        const val TAG = "FetchQuoteControl"
    }
}