package com.easylife.quotify.utils.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

abstract class IQuotifyDispatchers {

    abstract val main: CoroutineDispatcher

    abstract val io: CoroutineDispatcher

    abstract val default: CoroutineDispatcher
}