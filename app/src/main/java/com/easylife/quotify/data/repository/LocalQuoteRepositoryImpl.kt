package com.easylife.quotify.data.repository

import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.data.room.dao.QuoteDao
import com.easylife.quotify.domain.repository.LocalQuoteRepository
import com.easylife.quotify.utils.integration.config.QuotifyConfig

class LocalQuoteRepositoryImpl(
    private val quoteDao: QuoteDao,
): LocalQuoteRepository {

    override suspend fun insertQuote(quote: Quote) {
        quoteDao.insertQuote(quote)
    }

    override suspend fun insertAllQuote(quotes: List<Quote>) {
        quoteDao.insertAllQuote(quotes = quotes)
    }

    override suspend fun getQuotesByCategory(category: String): List<Quote> {
        return quoteDao.getQuotesByCategory(category)
    }

    override suspend fun getQuoteById(id: Int): Quote {
        return quoteDao.getQuoteById(id)
    }

    override suspend fun updateQuote(quote: Quote) {
        quoteDao.updateQuote(quote)
    }

    override suspend fun getFavoriteQuotesByPage(limit: Int, offset: Int): List<Quote> {
        return quoteDao.getFavoriteQuotesByPage(limit, offset)
    }

    override suspend fun getFavoriteQuotes(): List<Quote> {
        return quoteDao.getFavoriteQuotes()
    }

    override suspend fun deleteQuotesByCategory(category: String) {
        return quoteDao.deleteQuotesByCategory(category = category)
    }


}