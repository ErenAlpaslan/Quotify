package com.easylife.quotify.data.mapper

import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.data.models.QuoteListData
import com.easylife.quotify.utils.integration.config.QuotifyConfig

class QuoteListDataMapper(
    private val quotifyConfig: QuotifyConfig
) : DataMapper<List<Quote>?, List<QuoteListData>> {

    override fun transform(t: List<Quote>?): List<QuoteListData> {
        val transformedList = arrayListOf<QuoteListData>()
        t?.forEachIndexed { index, quote ->
            if (index % quotifyConfig.getConfig().adsPerQuote == 0 && index > 0) {
                transformedList.add(QuoteListData.Ads)
            }
            transformedList.add(QuoteListData.Content(quote))
        }

        return transformedList
    }
}