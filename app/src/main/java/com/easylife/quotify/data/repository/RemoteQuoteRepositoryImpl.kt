package com.easylife.quotify.data.repository

import android.util.Log
import com.easylife.quotify.common.AppConstant
import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.domain.repository.RemoteQuoteRepository
import com.easylife.quotify.domain.usecase.GetQuoteListDataUseCase
import com.easylife.quotify.utils.QuotifyResult
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RemoteQuoteRepositoryImpl(
    private val remote: DatabaseReference
) : RemoteQuoteRepository {

    override suspend fun getQuotesWithCategoryByPage(
        category: String,
        page: Int,
        rowCount: Int
    ): Flow<QuotifyResult<List<Quote>>> = callbackFlow {
        remote.child(AppConstant.DATABASE_QUOTES_PATH)
            .orderByChild("Category")
            .equalTo("inspiration")
            .orderByKey()
            .startAt()
            .limitToFirst(rowCount)
            .get()
            .addOnSuccessListener {
                this@callbackFlow.trySend(QuotifyResult.Success(data = it.children.map { childSnapShot ->
                    childSnapShot.value as Quote
                }))
            }.addOnFailureListener {error ->
                this@callbackFlow.trySend(QuotifyResult.Error(message = error.message))
            }.addOnCanceledListener {
                this@callbackFlow.trySend(QuotifyResult.Error(message = "Remote Canceled"))
            }
        awaitClose {
            Log.d(GetQuoteListDataUseCase.TAG, "=> here")
            this.cancel()
        }
    }

}