package com.easylife.quotify.ui.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.easylife.quotify.R
import com.easylife.quotify.base.BaseScreen
import com.easylife.quotify.ui.view.OnLifecycleEvent
import com.easylife.quotify.ui.view.QuotifyBottomSheet
import com.easylife.quotify.ui.view.QuotifyIconButton
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeScreen : BaseScreen<HomeViewModel, HomeNavigationActions>() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsState()

        val quotifyBottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
        )

        val coroutineScope = rememberCoroutineScope()

        OnLifecycleEvent { _, event ->
            when(event) {
                Lifecycle.Event.ON_RESUME -> viewModel.fetchInitialData()
                else -> {}
            }
        }

        QuotifyBottomSheet(bottomSheetState = quotifyBottomSheetScaffoldState) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                        },
                        actions = {
                            QuotifyIconButton(
                                icon = R.drawable.ic_crown,
                                onClick = {
                                    navigationActions.navigateToPaywall()
                                }
                            )
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                },
                content = { paddingValues ->
                    HomeContent(
                        uiState = uiState,
                        onShareClicked = {
                            coroutineScope.launch {
                                quotifyBottomSheetScaffoldState.bottomSheetState.expand()
                            }
                        },
                        onFavClicked = { quote ->
                            viewModel.onFavClicked(quote)
                        },
                        onNewQuoteSeen = { listData ->
                            viewModel.onNewQuoteSeen(listData)
                        },
                        paddingValues = paddingValues
                    )
                },
                bottomBar = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        QuotifyIconButton(
                            icon = Icons.Rounded.FavoriteBorder,
                            text = stringResource(id = R.string.button_favorites),
                            onClick = {
                                navigationActions.navigateToFavorites()
                            }
                        )

                        Row {
                            QuotifyIconButton(
                                icon = R.drawable.ic_brush,
                                onClick = {
                                    navigationActions.navigateToThemeSelection()
                                }
                            )
                            QuotifyIconButton(
                                icon = Icons.Rounded.Person,
                                onClick = {
                                    navigationActions.navigateToSettings()
                                }
                            )
                        }
                    }
                }
            )
        }
    }
}