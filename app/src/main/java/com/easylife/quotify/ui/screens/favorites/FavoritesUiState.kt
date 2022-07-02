package com.easylife.quotify.ui.screens.favorites

import com.easylife.quotify.data.models.Quote

data class FavoritesUiState(
    val favorites: List<Quote> = emptyList()
)
