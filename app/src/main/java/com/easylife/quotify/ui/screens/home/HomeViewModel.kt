package com.easylife.quotify.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.easylife.quotify.base.BaseViewModel
import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.domain.usecase.CacheQuoteListByCategoryUseCase
import com.easylife.quotify.domain.usecase.GetQuoteListByCategoryUseCase
import com.easylife.quotify.utils.QuotifyResult
import com.easylife.quotify.utils.integration.config.QuotifyConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getQuoteListByCategoryUseCase: GetQuoteListByCategoryUseCase,
    private val quotifyConfig: QuotifyConfig
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun onFavClicked(quote: Quote) {
    }

    init {
        fetchInitialData()
    }

    /**
     * TODO: category kullanıcı tarafından seçilmediyse app configden alaınacak
     *  - lastSeenIndex kontrolü yapılıp prefences'dan alınacak
     */
    private fun fetchInitialData() {
        viewModelScope.launch {
            getQuoteListByCategoryUseCase.execute(
                GetQuoteListByCategoryUseCase.Param(
                    lastSeenIndex = 0,
                    category = quotifyConfig.getConfig().category
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