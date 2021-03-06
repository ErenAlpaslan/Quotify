package com.easylife.quotify

import android.app.Application
import com.easylife.quotify.data.room.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class QuotifyApplication: Application() {

    private val moduleList = listOf<Module>(
        roomModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@QuotifyApplication)
            modules(moduleList)
        }
    }
}