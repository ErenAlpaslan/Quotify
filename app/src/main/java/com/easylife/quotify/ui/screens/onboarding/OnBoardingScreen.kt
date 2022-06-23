package com.easylife.quotify.ui.screens.onboarding

import android.widget.Space
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.easylife.quotify.R
import com.easylife.quotify.base.BaseScreen
import com.easylife.quotify.ui.theme.Red

class OnBoardingScreen : BaseScreen<OnBoardingViewModel, OnBoardingNavigationActions>() {

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun Content() {
        val brandingAnimationState = remember {
            MutableTransitionState(false).apply {
                targetState = true
            }
        }
        val contentDrawableAnimationState = remember {
            MutableTransitionState(false).apply {
                targetState = true
            }
        }

        val density = LocalDensity.current
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            AnimatedVisibility(
                visibleState = brandingAnimationState,
                enter = slideInVertically {
                    with(density) { -60.dp.roundToPx() }
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.quotify_brand),
                    contentDescription = "Quotify brand"
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedVisibility(
                visibleState = contentDrawableAnimationState,
                enter = fadeIn(animationSpec = tween(durationMillis = 1000, delayMillis = 500)),
                modifier = Modifier.fillMaxSize(0.5f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_relaxing_at_home),
                    contentDescription = "Quotify brand"
                )
            }
            
            Button(onClick = { viewModel.onForgotPasswordClicked() }) {
                Text("Forgot password")
            }
        }
    }

}