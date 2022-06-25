package com.easylife.quotify.domain.usecase

import com.easylife.quotify.base.BaseUseCase
import com.easylife.quotify.domain.repository.RemoteQuoteRepository
import com.easylife.quotify.utils.QuotifyResult
import com.easylife.quotify.utils.dispatchers.QuotifyDispatchers
import com.easylife.quotify.utils.integration.config.RemoteConfig
import com.easylife.quotify.utils.preferences.PreferencesKeys
import com.easylife.quotify.utils.preferences.PreferencesManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FetchInitialQuotesUseCase(
    private val remoteQuoteRepository: RemoteQuoteRepository,
    private val preferencesManager: PreferencesManager,
    private val remoteConfig: RemoteConfig,
    private val dispatchers: QuotifyDispatchers
): BaseUseCase<Boolean, Nothing>() {

    override suspend fun execute(params: Nothing?): Flow<QuotifyResult<Boolean>> = flow<QuotifyResult<Boolean>> {
        try {
            preferencesManager.getBoolean(PreferencesKeys.IS_FIRST_ENTER, true).collect{ isFirstEnter ->
                if (isFirstEnter) {
                    remoteQuoteRepository.getQuotesWithCategoryByPage(
                        category = "inspiration",
                        page = 0
                    )
                }
            }
            emit(QuotifyResult.Success(true))
        }catch (e: Exception) {
            emit(QuotifyResult.Error(e.message))
        }
    }.flowOn(dispatchers.io)
}