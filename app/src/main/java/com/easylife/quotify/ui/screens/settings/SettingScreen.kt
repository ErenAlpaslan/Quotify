package com.easylife.quotify.ui.screens.settings

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easylife.quotify.R
import com.easylife.quotify.base.BaseScreen
import com.easylife.quotify.ui.theme.Red

class SettingScreen : BaseScreen<SettingViewModel, SettingNavigationActions>() {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val scrollableState = rememberScrollableState(consumeScrollDelta = {
            0f
        })

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Rounded.Close, contentDescription = "Close")
                        }
                    }
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .scrollable(state = scrollableState, orientation = Orientation.Vertical)
                        .padding(it)
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            navigationActions.navigateToPayWall()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(text = stringResource(id = R.string.button_buy_premium))
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    SettingList(
                        map = mapOf(
                            R.string.settings_list_setting_title to listOf(Setting.NOTIFICATION),
                            R.string.settings_list_quotes_title to listOf(
                                Setting.FAVORITES,
                                Setting.SEEN
                            )
                        )
                    ) { setting ->
                        when (setting) {
                            Setting.NOTIFICATION -> {

                            }
                            Setting.FAVORITES -> {
                                navigationActions.navigateToFavorites()
                            }
                            Setting.SEEN -> {

                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(50.dp))

                }
            }
        )


    }

}