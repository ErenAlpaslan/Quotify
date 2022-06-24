package com.easylife.quotify.data.models

sealed class QuoteListData {
    object Ads: QuoteListData()
    data class Content(val model: Quote): QuoteListData()
}
