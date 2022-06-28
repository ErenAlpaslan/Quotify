package com.easylife.quotify.ui.screens.paywall

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.easylife.quotify.base.BaseScreen
import com.easylife.quotify.ui.theme.Blue

class PaywallScreen: BaseScreen<PaywallViewModel, PaywallNavigationActions>() {
    @Composable
    override fun Content() {
        Column(modifier = Modifier.background(Blue).fillMaxSize()) {
        }
    }
}