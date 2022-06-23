package com.easylife.quotify.ui.screens.onboarding

import androidx.compose.runtime.MutableState
import androidx.lifecycle.viewModelScope
import com.easylife.quotify.R
import com.easylife.quotify.base.BaseViewModel
import com.easylife.quotify.utils.preferences.PreferencesKeys
import com.easylife.quotify.utils.preferences.PreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val preferencesManager: PreferencesManager
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<OnBoardingUiState> = MutableStateFlow(
        OnBoardingUiState(
            pageList = listOf(
                OnBoardingPage(
                    title = R.string.onboarding_title_first,
                    description = R.string.onboarding_description_first,
                    isBrandVisible = true,
                    contentDrawable = R.drawable.ic_hello_chubs
                ),
                OnBoardingPage(
                    title = R.string.onboarding_title_second,
                    description = R.string.onboarding_description_second,
                    isBrandVisible = false,
                    contentDrawable = R.drawable.ic_reading_chubs
                ),
                OnBoardingPage(
                    title = R.string.onboarding_title_third,
                    description = R.string.onboarding_description_third,
                    isBrandVisible = false,
                    contentDrawable = R.drawable.ic_share_chubs
                ),
            ),
        )
    )
    val uiState: StateFlow<OnBoardingUiState> = _uiState

    /**
     * When user clicks next button updates button name and
     * increase the current page index
     */
    fun onNextClicked(currentPage: Int) {
        viewModelScope.launch {
            if (currentPage != _uiState.value.pageList.size - 1) {
                updateStateForNewPage(currentPage + 1)
            } else {
                preferencesManager.setBoolean(PreferencesKeys.IS_FIRST_ENTER, false)
                _uiState.update {
                    it.copy(isStartClicked = true)
                }
            }
        }
    }

    /**
     * When user clicks updates button name and
     * decrease the curren page index
     */
    fun onPreviousClicked(currentPage: Int) {
        updateStateForNewPage(currentPage - 1)
    }

    /**
     * Updates ui state with new current page index
     */
    private fun updateStateForNewPage(currentPage: Int) {
        if (currentPage == _uiState.value.pageList.size - 1) {
            _uiState.update {
                it.copy(currentPage = currentPage, buttonName = R.string.button_start)
            }
        }else {
            _uiState.update {
                it.copy(currentPage = currentPage, buttonName = R.string.button_next)
            }
        }
    }
}