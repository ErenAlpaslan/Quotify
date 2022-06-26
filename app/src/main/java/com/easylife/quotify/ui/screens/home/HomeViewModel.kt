package com.easylife.quotify.ui.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.easylife.quotify.base.BaseViewModel
import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.data.models.QuoteListData
import com.easylife.quotify.domain.usecase.CacheQuoteListByCategoryUseCase
import com.easylife.quotify.domain.usecase.FavQuoteUseCase
import com.easylife.quotify.domain.usecase.GetQuoteListByCategoryUseCase
import com.easylife.quotify.utils.QuotifyResult
import com.easylife.quotify.utils.dispatchers.QuotifyDispatchers
import com.easylife.quotify.utils.integration.config.QuotifyConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val quotifyConfig: QuotifyConfig,
    private val getQuoteListByCategoryUseCase: GetQuoteListByCategoryUseCase,
    private val favQuoteUseCase: FavQuoteUseCase,
    private val dispatchers: QuotifyDispatchers
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> =
        _uiState.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), _uiState.value)

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
                    is QuotifyResult.Success -> {
                        result.data?.let { list ->
                            _uiState.update {
                                it.copy(data = list)
                            }
                        }
                    }
                }
            }
        }
    }

    fun onFavClicked(quote: QuoteListData) {
        viewModelScope.launch {
            favQuoteUseCase.execute(FavQuoteUseCase.Param(quote, _uiState.value.data))
                .collect { result ->
                    when (result) {
                        is QuotifyResult.Error -> _error.postValue(result.message)
                        is QuotifyResult.Success -> {
                            result.data?.let { map ->
                                _uiState.update {
                                    it.copy(
                                        data = map.values.first(),
                                        currentQuote = map.keys.first() as QuoteListData.Content,
                                        recompose = it.recompose.not()
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }

    fun onNewQuoteSeen(quoteListData: QuoteListData?) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(currentQuote = quoteListData)
            }
        }
    }

}