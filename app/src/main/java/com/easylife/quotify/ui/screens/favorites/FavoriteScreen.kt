package com.easylife.quotify.ui.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.easylife.quotify.base.BaseScreen
import com.easylife.quotify.ui.theme.Black

class FavoriteScreen: BaseScreen<FavoriteViewModel, FavoriteNavigationActions>() {
    @Composable
    override fun Content() {
        Column(modifier = Modifier.fillMaxSize().background(Black)) {

        }
    }
}