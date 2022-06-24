package com.easylife.quotify.utils.integration.config

import org.koin.dsl.module

val remoteConfigModule = module {
    single { RemoteConfig() }
}