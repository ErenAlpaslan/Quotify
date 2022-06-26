package com.easylife.quotify

import android.app.Application
import com.easylife.quotify.common.appModule
import com.easylife.quotify.data.mapper.mapperModule
import com.easylife.quotify.data.repositoryModule
import com.easylife.quotify.data.room.roomModule
import com.easylife.quotify.domain.useCaseModule
import com.easylife.quotify.ui.screens.home.homeModule
import com.easylife.quotify.ui.screens.onboarding.onBoardingModule
import com.easylife.quotify.ui.screens.splash.splashModule
import com.easylife.quotify.utils.integration.config.remoteConfigModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class QuotifyApplication : Application() {

    private val moduleList = listOf<Module>(
        appModule,
        roomModule,
        repositoryModule,
        useCaseModule,
        remoteConfigModule,
        mapperModule,
        splashModule,
        onBoardingModule,
        homeModule
    )

    override fun onCreate() {
        super.onCreate()
        instance = this
        FirebaseApp.initializeApp(this@QuotifyApplication.applicationContext)
        startKoin {
            androidContext(this@QuotifyApplication)
            modules(moduleList)
        }
    }

    companion object {
        lateinit var instance: QuotifyApplication
    }
}