package com.easylife.quotify.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.easylife.quotify.data.models.QuoteListData
import com.easylife.quotify.ui.theme.Green

@Composable
fun QuoteListItem(
    item: QuoteListData
) {
    when (item) {
        QuoteListData.Ads -> {
            Column(modifier = Modifier.fillMaxSize().background(Green)) {
            }
        }
        is QuoteListData.Content -> {
            Text(
                text = item.model.quote ?: "",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}