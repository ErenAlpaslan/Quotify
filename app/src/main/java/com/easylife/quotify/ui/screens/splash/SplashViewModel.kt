package com.easylife.quotify.ui.screens.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.easylife.quotify.base.BaseViewModel
import com.easylife.quotify.ui.navigation.Screen
import com.easylife.quotify.utils.preferences.PreferencesKeys
import com.easylife.quotify.utils.preferences.PreferencesManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val preferencesManager: PreferencesManager
) : BaseViewModel() {

    private val _uiState: MutableLiveData<SplashUiState> = MutableLiveData(SplashUiState())
    val uiState: LiveData<SplashUiState> = _uiState

    init {
        handleUserNavigation()
    }

    private fun handleUserNavigation() {
        viewModelScope.launch {
            preferencesManager.getBoolean(PreferencesKeys.IS_FIRST_ENTER, true)
                .collect { isFirstEnter ->
                    _uiState.postValue(SplashUiState(isFirstEnter = isFirstEnter))
                }
        }
    }

}