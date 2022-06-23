package com.easylife.quotify.ui.screens.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.easylife.quotify.R

data class OnBoardingUiState(
    val currentPage: Int = 0,
    val pageList: List<OnBoardingPage>,
    val isStartClicked: Boolean = false,
    @StringRes val buttonName: Int = R.string.button_next
)

data class OnBoardingPage(
    @StringRes val title: Int,
    @StringRes val description: Int,
    val isBrandVisible: Boolean,
    @DrawableRes val contentDrawable: Int
)