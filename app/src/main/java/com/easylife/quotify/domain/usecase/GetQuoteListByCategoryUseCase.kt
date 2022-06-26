package com.easylife.quotify.domain.usecase

import android.util.Log
import com.easylife.quotify.base.BaseUseCase
import com.easylife.quotify.data.mapper.QuoteListDataMapper
import com.easylife.quotify.data.models.QuoteListData
import com.easylife.quotify.domain.repository.LocalQuoteRepository
import com.easylife.quotify.utils.QuotifyResult
import com.easylife.quotify.utils.dispatchers.QuotifyDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetQuoteListByCategoryUseCase(
    private val cacheQuoteListByCategoryUseCase: CacheQuoteListByCategoryUseCase,
    private val localQuoteRepository: LocalQuoteRepository,
    private val dispatchers: QuotifyDispatchers,
    private val quoteListDataMapper: QuoteListDataMapper
) : BaseUseCase<List<QuoteListData>, GetQuoteListByCategoryUseCase.Param>() {

    data class Param(
        val category: String,
        val lastSeenIndex: Int
    )

    /**
     * TODO: Add version control for categories that can come from remote config
     */
    override suspend fun execute(params: Param): Flow<QuotifyResult<List<QuoteListData>>> = flow {
        val quotesFromCache = localQuoteRepository.getQuotesByCategory(params.category)

        if (quotesFromCache.isNotEmpty()) {
            emit(QuotifyResult.Success(quoteListDataMapper.transform(quotesFromCache)))
        } else {
            cacheQuoteListByCategoryUseCase.execute(params).collect { cacheResult ->
                when (cacheResult) {
                    is QuotifyResult.Error -> emit(
                        QuotifyResult.Error<List<QuoteListData>>(
                            cacheResult.message
                        )
                    )
                    is QuotifyResult.Success -> {
                        emit(QuotifyResult.Success(quoteListDataMapper.transform(cacheResult.data)))
                    }
                }
            }
        }
    }.flowOn(dispatchers.io)
}