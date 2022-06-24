package com.easylife.quotify.utils.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey

object PreferencesKeys {
    const val PREFERENCES_NAME = "QUOTIFY_PREFERENCES"
    val IS_FIRST_ENTER = booleanPreferencesKey("IS_FIRST_ENTER")
    val LAST_SEEN_PAGE = intPreferencesKey("LAST_SEEN_PAGE")
}