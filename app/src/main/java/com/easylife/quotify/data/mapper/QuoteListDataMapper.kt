package com.easylife.quotify.data.mapper

import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.data.models.QuoteListData

class QuoteListDataMapper: DataMapper<Quote, List<QuoteListData>> {

    override fun transform(list: List<Quote>): List<QuoteListData> {
        val transformedList = arrayListOf<QuoteListData>()
        list.forEachIndexed { index, quote ->
            if (index % ADS_OFFSET == 0) {
                transformedList.add(QuoteListData.Ads)
            }
            transformedList.add(QuoteListData.Content(quote))
        }

        return transformedList
    }

    companion object {
        const val ADS_OFFSET = 5
    }

}