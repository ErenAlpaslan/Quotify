package com.easylife.quotify.ui.screens.home

import androidx.compose.runtime.Immutable
import com.easylife.quotify.base.BaseUiState
import com.easylife.quotify.data.models.QuoteListData

data class HomeUiState(
    val data: List<QuoteListData> = emptyList(),
    val page: Int = 0,
    val currentQuote: QuoteListData? = null,
    val recompose: Boolean = false,
)