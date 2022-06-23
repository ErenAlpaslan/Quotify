package com.easylife.quotify.ui.screens.splash

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.easylife.quotify.R
import com.easylife.quotify.base.BaseActions
import com.easylife.quotify.base.BaseScreen
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import kotlinx.coroutines.delay

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
                painter = painterResource(id = R.drawable.quotify_logo),
                contentDescription = "Qoutify Logo",
                modifier = Modifier.size(150.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
        }
    }

    @Composable
    private fun Navigation(isFirstEnter: Boolean? = false) {
        LaunchedEffect(key1 = "") {
            delay(3000)
            when(isFirstEnter) {
                true -> navigationActions.navigateToOnBoarding()
                else -> navigationActions.navigateToHome()
            }
        }
    }

    fun Context.findActivity(): Activity? = when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }
}