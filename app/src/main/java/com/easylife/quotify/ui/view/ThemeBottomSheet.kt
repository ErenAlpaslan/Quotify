package com.easylife.quotify.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easylife.quotify.ui.theme.Green

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuotifyBottomSheet(
    bottomSheetState: BottomSheetScaffoldState,
    content: @Composable () -> Unit
) {
    BottomSheetScaffold(
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Green),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Share")
            }
        },
        sheetPeekHeight = 0.dp,
        content = {
            content()
        },
        scaffoldState = bottomSheetState
    )
}