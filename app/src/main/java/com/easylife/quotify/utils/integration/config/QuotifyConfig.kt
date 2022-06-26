package com.easylife.quotify.utils.integration.config

import com.easylife.quotify.BuildConfig
import com.easylife.quotify.R
import com.easylife.quotify.data.models.AppConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.gson.Gson
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class QuotifyConfig(): KoinComponent {

    companion object {
        const val QUOTE_ROW_COUNT = 10
    }

    private var remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
    private val gson: Gson by inject()

    init {
        val configSettings = remoteConfigSettings {
            if (BuildConfig.DEBUG) {
                minimumFetchIntervalInSeconds = 0
            }else {
                minimumFetchIntervalInSeconds = 3600
            }
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate()
    }

    fun getBoolean(key: String): Boolean = remoteConfig.getBoolean(key)

    fun getInt(key: String): Int = remoteConfig.getDouble(key).toInt()

    fun getString(key: String): String = remoteConfig.getString(key)

    fun getConfig(): AppConfig {
        return try {
            val jsonString = remoteConfig.getString(RemoteConfigKeys.QUOTIFY_CONFIG)
            gson.fromJson(jsonString, AppConfig::class.java)
        }catch (e: Exception) {
            AppConfig()
        }
    }
}