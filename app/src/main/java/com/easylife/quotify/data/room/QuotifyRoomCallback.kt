package com.easylife.quotify.data.room

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.easylife.quotify.utils.dispatchers.QuotifyDispatchers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object QuotifyRoomCallback: RoomDatabase.Callback(), KoinComponent {

    private val dispatchers: QuotifyDispatchers by inject()

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        /*CoroutineScope(dispatchers.io).launch {
            userDao.insertUser(User(id = 0))
        }*/
    }

}