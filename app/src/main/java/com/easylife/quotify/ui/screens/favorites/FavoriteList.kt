package com.easylife.quotify.ui.screens.favorites

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easylife.quotify.R
import com.easylife.quotify.data.models.Quote

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteList(
    list: List<Quote>,
    onShareClicked: (Quote) -> Unit,
    onDeleteClicked: (Quote) -> Unit,
    onItemClicked: (Quote) -> Unit
) {
    LazyColumn() {
        itemsIndexed(items = list, key = {_, item -> item.id  }) { _, item ->
            FavoriteListItem(
                item = item,
                modifier = Modifier.animateItemPlacement(tween(durationMillis = 600)),
                onShareClicked = {
                    onShareClicked(item)
                },
                onDeleteClicked = {
                    onDeleteClicked(item)
                }
            ) {
                onItemClicked(item)
            }
        }
    }
}

@Composable
fun FavoriteListItem(
    item: Quote,
    modifier: Modifier,
    onShareClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onClick: () -> Unit,
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.CenterVertically, unbounded = true)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.quote ?: "",
                modifier = Modifier
                    .padding(start = 20.dp)
                    .weight(1f),
                style = MaterialTheme.typography.labelSmall
            )
            Column {
                IconButton(onClick = { expanded = true }) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "")
                }

                DropdownMenu(expanded = expanded, onDismissRequest = {
                    expanded = false
                }) {
                    listOf(
                        stringResource(id = R.string.favorites_share_quote),
                        stringResource(id = R.string.favorites_delete),
                    ).forEachIndexed { index, s ->
                        DropdownMenuItem(
                            text = {
                                Text(text = s)
                            },
                            onClick = {
                                expanded = false
                                when (index) {
                                    0 -> onShareClicked()
                                    1 -> onDeleteClicked()
                                }
                            })
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Divider()
    }
    Spacer(modifier = Modifier.height(20.dp))
}