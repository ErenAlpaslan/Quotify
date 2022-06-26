package com.easylife.quotify.domain.usecase

import com.easylife.quotify.base.BaseUseCase
import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.domain.repository.LocalQuoteRepository
import com.easylife.quotify.domain.repository.RemoteQuoteRepository
import com.easylife.quotify.utils.QuotifyResult
import com.easylife.quotify.utils.dispatchers.QuotifyDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CacheQuoteListByCategoryUseCase(
    private val localQuoteRepository: LocalQuoteRepository,
    private val remoteQuoteRepository: RemoteQuoteRepository,
    private val dispatchers: QuotifyDispatchers,
) : BaseUseCase<List<Quote>, GetQuoteListByCategoryUseCase.Param>() {

    override suspend fun execute(params: GetQuoteListByCategoryUseCase.Param): Flow<QuotifyResult<List<Quote>>> =
        flow {
            val localQuotesByCategory = localQuoteRepository.getQuotesByCategory(params.category)
            remoteQuoteRepository.getQuotesWithCategory(
                category = params.category
            ).collect { remoteResult ->
                when (remoteResult) {
                    is QuotifyResult.Error -> emit(
                        QuotifyResult.Error<List<Quote>>(
                            remoteResult.message
                        )
                    )
                    is QuotifyResult.Success -> {
                        remoteResult.data?.let { newQuoteList ->
                            if (localQuotesByCategory.isNotEmpty()) {
                                localQuotesByCategory.map { oldQuote ->
                                    newQuoteList.find { it.id == oldQuote.id }?.apply {
                                        isFavorite = oldQuote.isFavorite
                                        isShown = oldQuote.isShown
                                        isCollected = oldQuote.isCollected
                                    }
                                }
                            }
                            localQuoteRepository.deleteQuotesByCategory(params.category)
                            localQuoteRepository.insertAllQuote(newQuoteList)
                            emit(QuotifyResult.Success(newQuoteList))
                        }
                            ?: emit(QuotifyResult.Error<List<Quote>>("Empty quote list try again later"))
                    }
                }
            }
        }.flowOn(dispatchers.io)


}