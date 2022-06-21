package com.easylife.quotify.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.easylife.quotify.base.BaseScreen
import com.easylife.quotify.ui.theme.Green

class HomeScreen: BaseScreen<HomeViewModel, HomeNavigationActions>() {
    @Composable
    override fun Content() {
        var trigger by remember {
            mutableStateOf(false)
        }
        
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Green)) {
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Trigger")
            }
        }
    }
}