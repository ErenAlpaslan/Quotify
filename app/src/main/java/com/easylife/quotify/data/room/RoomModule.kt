package com.easylife.quotify.data.room

import androidx.room.Room
import com.easylife.quotify.common.AppConstant
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
    single { Gson() }
    /*single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
        AppConstant.QUOTIFY_DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }*/
    //single { get<AppDatabase>().taskDao() }
    //single { get<AppDatabase>().categoryDao() }
}