package com.easylife.quotify.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.easylife.quotify.base.BaseScreen

class SplashScreen : BaseScreen<SplashViewModel, SplashNavigationActions>() {

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.observeAsState()

        if (uiState?.isFirstEnter != null) {
            Navigation(uiState?.isFirstEnter)
        }
    }

    @Composable
    private fun Navigation(isFirstEnter: Boolean? = false) {
        LaunchedEffect(key1 = "") {
            when(isFirstEnter) {
                true -> navigationActions.navigateToOnBoarding()
                else -> navigationActions.navigateToHome()
            }
        }
    }
}