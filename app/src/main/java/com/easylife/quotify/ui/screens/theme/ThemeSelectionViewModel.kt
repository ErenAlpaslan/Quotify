package com.easylife.quotify.ui.screens.theme

import androidx.lifecycle.viewModelScope
import com.easylife.quotify.base.BaseViewModel
import com.easylife.quotify.domain.usecase.GetThemesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ThemeSelectionViewModel(
    private val getThemesUseCase: GetThemesUseCase
): BaseViewModel() {

    private val _uiState: MutableStateFlow<ThemeUiState> = MutableStateFlow(ThemeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        initializeThemes()
    }

    private fun initializeThemes() {
        viewModelScope.launch {

        }
    }

}