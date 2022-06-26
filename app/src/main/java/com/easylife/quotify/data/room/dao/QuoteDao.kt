package com.easylife.quotify.data.room.dao

import androidx.room.*
import com.easylife.quotify.data.models.Quote

@Dao
interface QuoteDao {

    @Insert
    suspend fun insertQuote(quote: Quote)

    @Insert
    suspend fun insertAllQuote(quotes: List<Quote>)

    @Query("SELECT * FROM Quotes WHERE Category = :category")
    suspend fun getQuotesByCategory(category: String): List<Quote>

    @Query("SELECT * FROM Quotes WHERE id = :id")
    suspend fun getQuoteById(id: Int): Quote

    @Update
    suspend fun updateQuote(quote: Quote)

    @Query("SELECT * FROM Quotes WHERE isFavorite = 1 LIMIT :limit OFFSET :offset")
    suspend fun getFavoriteQuotesByPage(limit: Int, offset: Int): List<Quote>

    @Query("SELECT * FROM Quotes WHERE isFavorite = 1")
    suspend fun getFavoriteQuotes(): List<Quote>

    @Query("DELETE FROM Quotes WHERE Category = :category")
    suspend fun deleteQuotesByCategory(category: String)

}