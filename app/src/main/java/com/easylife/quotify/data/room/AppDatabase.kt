package com.easylife.quotify.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.data.room.dao.QuoteDao

@Database(
    entities = [
        Quote::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun quoteDao(): QuoteDao
}