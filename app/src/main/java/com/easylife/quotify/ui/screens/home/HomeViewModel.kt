package com.easylife.quotify.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.easylife.quotify.base.BaseViewModel
import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.domain.usecase.GetQuoteListDataUseCase
import com.easylife.quotify.utils.QuotifyResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getQuoteListDataUseCase: GetQuoteListDataUseCase
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun onFavClicked(quote: Quote) {
    }

    init {
        fetchInitialData()
    }

    private fun fetchInitialData() {
        viewModelScope.launch {
            getQuoteListDataUseCase.execute(
                GetQuoteListDataUseCase.Param(
                    _uiState.value.page,
                    0,
                    "inspiration"
                )
            ).collect { result ->
                when (result) {
                    is QuotifyResult.Error -> _error.postValue(result.message)
                    is QuotifyResult.Success -> _uiState.update {
                        it.copy(data = result.data)
                    }
                }
            }
        }
    }


}