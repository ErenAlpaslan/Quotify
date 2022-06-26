package com.easylife.quotify.domain.repository

import com.easylife.quotify.data.models.Quote

interface LocalQuoteRepository {

    suspend fun insertQuote(quote: Quote)

    suspend fun insertAllQuote(quotes: List<Quote>)

    suspend fun getQuotesByCategory(category: String): List<Quote>

    suspend fun getQuoteById(id: Int): Quote

    suspend fun updateQuote(quote: Quote)

    suspend fun getFavoriteQuotesByPage(limit: Int, offset: Int): List<Quote>

    suspend fun getFavoriteQuotes(): List<Quote>

    suspend fun deleteQuotesByCategory(category: String)
}