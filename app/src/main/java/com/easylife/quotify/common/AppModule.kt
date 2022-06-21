package com.easylife.quotify.common

import com.easylife.quotify.utils.preferences.PreferencesManager
import org.koin.dsl.module

val appModule = module {
    single { PreferencesManager(get()) }
}