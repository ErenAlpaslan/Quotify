package com.easylife.quotify.domain.repository

import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.utils.QuotifyResult
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import kotlinx.coroutines.flow.Flow

interface RemoteQuoteRepository {

    suspend fun getQuotesWithCategory(
        category: String
    ): Flow<QuotifyResult<List<Quote>>>

}