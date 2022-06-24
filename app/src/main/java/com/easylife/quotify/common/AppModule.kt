package com.easylife.quotify.common

import com.easylife.quotify.utils.preferences.PreferencesManager
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val appModule = module {
    single { PreferencesManager(get()) }
    single { Firebase.database.reference }
}