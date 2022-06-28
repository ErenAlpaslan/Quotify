package com.easylife.quotify.ui.screens.theme

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.easylife.quotify.base.BaseScreen
import com.easylife.quotify.ui.theme.Green

class ThemeSelectionScreen: BaseScreen<ThemeSelectionViewModel, ThemeSelectionNavigationAction>() {
    @Composable
    override fun Content() {
        Column(modifier = Modifier.background(Green).fillMaxSize()) {
            
        }
    }

}