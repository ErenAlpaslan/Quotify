package com.easylife.quotify.domain.usecase

import com.easylife.quotify.base.BaseUseCase
import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.domain.repository.LocalQuoteRepository
import com.easylife.quotify.utils.QuotifyResult
import com.easylife.quotify.utils.dispatchers.QuotifyDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetFavoritesUseCase(
    private val localQuoteRepository: LocalQuoteRepository,
    private val dispatchers: QuotifyDispatchers
): BaseUseCase<List<Quote>, Any?>() {
    override suspend fun execute(params: Any?): Flow<QuotifyResult<List<Quote>>> = flow {
        try {
            val favorites = localQuoteRepository.getFavoriteQuotes()
            emit(QuotifyResult.Success(favorites))
        }catch (e: Exception) {
            emit(QuotifyResult.Error(e.message))
        }
    }.flowOn(dispatchers.io)
}