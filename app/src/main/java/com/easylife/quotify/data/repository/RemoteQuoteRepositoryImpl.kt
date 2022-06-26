package com.easylife.quotify.data.repository

import android.util.Log
import com.easylife.quotify.BuildConfig
import com.easylife.quotify.common.AppConstant
import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.domain.repository.RemoteQuoteRepository
import com.easylife.quotify.utils.QuotifyResult
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RemoteQuoteRepositoryImpl(
    private val remote: DatabaseReference
) : RemoteQuoteRepository {

    override suspend fun getQuotesWithCategory(
        category: String,
    ): Flow<QuotifyResult<List<Quote>>> = callbackFlow {
        remote.child(AppConstant.DATABASE_QUOTES_PATH)
            .orderByChild(AppConstant.DATABASE_CATEGORY_PATH)
            .equalTo(category)
            .apply {
                if (BuildConfig.DEBUG)
                    limitToFirst(10)
            }
            .get()
            .addOnSuccessListener {
                val data = arrayListOf<Quote>()
                it.children.map { childSnapShot ->
                    try {
                        childSnapShot.getValue<Quote>()?.let { quote ->
                            Log.d("QControl", "=> ${childSnapShot.key.toString()}")
                            quote.id = childSnapShot.key.toString()
                            data.add(quote)
                        }
                    }catch (e: Exception) {
                        Log.d("ErrorControl", "=> ${e.message}")
                    }
                }
                this@callbackFlow.trySend(QuotifyResult.Success(data))
            }.addOnFailureListener {error ->
                this@callbackFlow.trySend(QuotifyResult.Error(message = error.message))
            }.addOnCanceledListener {
                this@callbackFlow.trySend(QuotifyResult.Error(message = "Remote Canceled"))
            }
        awaitClose {
            this.cancel()
        }
    }

}