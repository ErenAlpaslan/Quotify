package com.easylife.quotify.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.easylife.quotify.data.models.Quote
import com.easylife.quotify.data.models.QuoteListData
import com.easylife.quotify.ui.theme.Red
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    onShareClicked: (Quote) -> Unit,
    onFavClicked: (QuoteListData) -> Unit,
    onNewQuoteSeen: (QuoteListData?) -> Unit,
    paddingValues: PaddingValues
) {
    val uiState by viewModel.uiState.collectAsState()

    var seenItem: QuoteListData? by remember {
        mutableStateOf(if (uiState.data.isNotEmpty()) uiState.data.get(0) else null)
    }
    seenItem = uiState.currentQuote

    var isFavorite by remember(seenItem) {
        mutableStateOf(if (seenItem is QuoteListData.Content) (seenItem as QuoteListData.Content).model.isFavorite else false )
    }

    var pagerState = rememberPagerState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
    ) {
        val (quoteRef, actionRowRef) = createRefs()
        VerticalPager(
            count = uiState.data.size ?: 0,
            modifier = Modifier
                .constrainAs(quoteRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(actionRowRef.top)
                },
            state = pagerState,
        ) { pageIndex: Int ->
            val item = uiState.data.get(pageIndex)
            onNewQuoteSeen(uiState.data.get(pagerState.currentPage))
            QuoteListItem(item = item)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.constrainAs(actionRowRef) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        ) {
            if (seenItem is QuoteListData.Content) {
                IconButton(onClick = { onShareClicked((seenItem as QuoteListData.Content).model) }) {
                    Icon(imageVector = Icons.Rounded.Share, contentDescription = "Share")
                }
                IconButton(onClick = {
                    onFavClicked((seenItem as QuoteListData.Content))
                    isFavorite = isFavorite.not()
                }) {
                    Icon(
                        imageVector = if (isFavorite) {
                            Icons.Rounded.Favorite
                        } else {
                            Icons.Rounded.FavoriteBorder
                        },
                        contentDescription = "Favorite"
                    )
                }
            }
        }
    }
}