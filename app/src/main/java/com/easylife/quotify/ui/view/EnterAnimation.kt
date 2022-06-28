package com.easylife.quotify.ui.view

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    val animationState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = animationState,
        enter = slideInVertically(
            initialOffsetY = { 1000 },
            animationSpec = tween(1000, easing = LinearOutSlowInEasing)
        )+ fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
        content = { content() }
    )
}