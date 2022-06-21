package com.easylife.quotify.ui.screens.onboarding

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easylife.quotify.R
import com.easylife.quotify.base.BaseScreen
import com.easylife.quotify.ui.theme.Red

class OnBoardingScreen: BaseScreen<OnBoardingViewModel, OnBoardingNavigationActions>() {

    @Composable
    override fun Content() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Red)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = stringResource(id =  R.string.app_name))
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { navigationActions.navigateToHome() }) {
                
            }
        }
    }

}