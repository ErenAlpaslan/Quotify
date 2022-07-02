package com.easylife.quotify.ui.screens.favorites

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.easylife.quotify.base.BaseViewModel
import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.domain.usecase.GetFavoritesUseCase
import com.easylife.quotify.domain.usecase.UnFavoriteUseCase
import com.easylife.quotify.utils.QuotifyResult
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getFavovoritesUseCase: GetFavoritesUseCase,
    private val unFavoriteUseCase: UnFavoriteUseCase
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<FavoritesUiState> = MutableStateFlow(FavoritesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            getFavovoritesUseCase.execute(null).collect { result ->
                when (result) {
                    is QuotifyResult.Error -> _error.postValue(result.message)
                    is QuotifyResult.Success -> _uiState.update {
                        it.copy(favorites = result.data ?: emptyList())
                    }
                }
            }
        }
    }

    fun unFavorite(quote: Quote) {
        viewModelScope.launch {
            unFavoriteUseCase.execute(
                UnFavoriteUseCase.Param(
                    list = _uiState.value.favorites.toMutableList(),
                    quote = quote
                )
            ).collect { result ->
                when (result) {
                    is QuotifyResult.Error -> _error.postValue(result.message)
                    is QuotifyResult.Success -> _uiState.update {
                        it.copy(favorites = result.data ?: emptyList())
                    }
                }
            }
        }
    }
}