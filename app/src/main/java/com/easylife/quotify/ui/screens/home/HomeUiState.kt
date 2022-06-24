package com.easylife.quotify.ui.screens.home

import com.easylife.quotify.data.models.QuoteListData

data class HomeUiState(
    val data: List<QuoteListData>? = null,
    val page: Int = 0
)