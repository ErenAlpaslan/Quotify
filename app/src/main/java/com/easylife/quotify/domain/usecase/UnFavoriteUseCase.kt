package com.easylife.quotify.domain.usecase

import com.easylife.quotify.base.BaseUseCase
import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.domain.repository.LocalQuoteRepository
import com.easylife.quotify.utils.QuotifyResult
import com.easylife.quotify.utils.dispatchers.QuotifyDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UnFavoriteUseCase(
    private val localQuoteRepository: LocalQuoteRepository,
    private val dispatchers: QuotifyDispatchers
): BaseUseCase<MutableList<Quote>, UnFavoriteUseCase.Param>() {
    data class Param(
        val list: MutableList<Quote>,
        val quote: Quote
    )

    override suspend fun execute(params: Param): Flow<QuotifyResult<MutableList<Quote>>> = flow {
        try {
            params.quote.isFavorite = false
            params.list.removeIf { it.id == params.quote.id }
            localQuoteRepository.updateQuote(params.quote)
            emit(QuotifyResult.Success(params.list))
        }catch (e: Exception) {
            emit(QuotifyResult.Error(e.message))
        }
    }.flowOn(dispatchers.io)

}