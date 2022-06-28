package com.easylife.quotify.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.easylife.quotify.base.BaseScreen
import com.easylife.quotify.ui.theme.Red

class SettingScreen: BaseScreen<SettingViewModel, SettingNavigationActions>() {

    @Composable
    override fun Content() {
        Column(modifier = Modifier.fillMaxSize().background(Red)) {

        }
    }

}