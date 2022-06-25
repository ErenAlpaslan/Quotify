package com.easylife.quotify.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
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
    selectedTheme: String,
    contentList: List<QuoteListData>,
    onShareClicked: (Quote) -> Unit,
    onFavClicked: (Quote) -> Unit,
    paddingValues: PaddingValues
) {
    var seenItem: QuoteListData? by remember {
        mutableStateOf(if (contentList.isNotEmpty()) contentList[0] else null)
    }

    var pagerState = rememberPagerState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
    ) {
        val (quoteRef, actionRowRef) = createRefs()
        VerticalPager(
            count = contentList.size,
            modifier = Modifier
                .constrainAs(quoteRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(actionRowRef.top)
                },
            state = pagerState,
        ) { pageIndex: Int ->
            val item = contentList[pageIndex]
            seenItem = contentList[pagerState.currentPage]
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
                IconButton(onClick = { onFavClicked((seenItem as QuoteListData.Content).model) }) {
                    Icon(
                        imageVector = if ((seenItem as QuoteListData.Content).model.isFavorite) {
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