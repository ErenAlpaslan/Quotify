package com.easylife.quotify.ui.screens.splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.easylife.quotify.R
import com.easylife.quotify.base.BaseActions
import com.easylife.quotify.base.BaseScreen

class SplashScreen : BaseScreen<SplashViewModel, SplashNavigationActions>() {

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.observeAsState()

        if (uiState?.isFirstEnter != null) {
            Navigation(uiState?.isFirstEnter)
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier.size(150.dp)
            )
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