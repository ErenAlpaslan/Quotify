package com.easylife.quotify.domain.usecase

import com.easylife.quotify.base.BaseUseCase
import com.easylife.quotify.data.models.Theme
import com.easylife.quotify.utils.QuotifyResult
import com.easylife.quotify.utils.dispatchers.QuotifyDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetThemesUseCase(
    private val dispatchers: QuotifyDispatchers
): BaseUseCase<List<Theme>, Any?>() {
    override suspend fun execute(params: Any?): Flow<QuotifyResult<List<Theme>>> = flow {
        emit(QuotifyResult.Success<List<Theme>>(emptyList()))
    }.flowOn(dispatchers.io)

}