package com.easylife.quotify.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import org.koin.android.BuildConfig

@Database(
    entities = [
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

}