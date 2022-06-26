package com.easylife.quotify.domain.usecase

import com.easylife.quotify.base.BaseUseCase
import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.data.models.QuoteListData
import com.easylife.quotify.domain.repository.LocalQuoteRepository
import com.easylife.quotify.utils.QuotifyResult
import com.easylife.quotify.utils.dispatchers.QuotifyDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FavQuoteUseCase(
    private val localQuoteRepository: LocalQuoteRepository,
    private val dispatchers: QuotifyDispatchers
): BaseUseCase<Map<QuoteListData, List<QuoteListData>>, FavQuoteUseCase.Param>() {
    data class Param(
        val quote: QuoteListData,
        val quotes: List<QuoteListData>?
    )

    override suspend fun execute(params: Param): Flow<QuotifyResult<Map<QuoteListData, List<QuoteListData>>>> = flow {
        try {
            if (params.quote is QuoteListData.Content) {
                params.quote.apply {
                    model.isFavorite = model.isFavorite.not()
                }
                params.quotes?.find {
                    it is QuoteListData.Content && it.model.id == params.quote.model.id
                }?.apply {
                    (this as QuoteListData.Content).model.isFavorite = params.quote.model.isFavorite
                }

                localQuoteRepository.updateQuote(params.quote.model)
            }
            emit(QuotifyResult.Success(
                mapOf(params.quote to (params.quotes ?: emptyList()))
            ))
        }catch (e: Exception) {
            emit(QuotifyResult.Error(e.message))
        }
    }.flowOn(dispatchers.io)

}