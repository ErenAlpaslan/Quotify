package com.easylife.quotify.base

import com.easylife.quotify.utils.QuotifyResult
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<T : Any, in Params> {
    abstract suspend fun execute(params: Params?): Flow<QuotifyResult<T>>
}