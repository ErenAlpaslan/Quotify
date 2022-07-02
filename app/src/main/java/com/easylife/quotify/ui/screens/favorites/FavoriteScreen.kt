package com.easylife.quotify.ui.screens.favorites

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.easylife.quotify.R
import com.easylife.quotify.base.BaseScreen
import com.easylife.quotify.ui.theme.Black
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

class FavoriteScreen : BaseScreen<FavoriteViewModel, FavoriteNavigationActions>() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsState()

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.settings_favorites))
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Rounded.Close, contentDescription = "")
                        }
                    }
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                ) {
                    FavoriteList(
                        list = uiState.favorites,
                        onShareClicked = {

                        },
                        onDeleteClicked = { quote ->
                            viewModel.unFavorite(quote)
                        },
                        onItemClicked = { quote ->
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                SELECTED_FAVORITE_QUOTE,
                                quote
                            )
                            navController.popBackStack()
                        }
                    )
                }
            }
        )
    }

    companion object {
        const val SELECTED_FAVORITE_QUOTE = "SELECTED_FAVORITE_QUOTE"
    }
}