package com.easylife.quotify.ui.screens.home

import com.easylife.quotify.data.models.QuoteListData

data class HomeUiState(
    val data: List<QuoteListData> = emptyList(),
    val page: Int = 0,
    val currentQuote: QuoteListData? = null,
    val recompose: Boolean = false,
)