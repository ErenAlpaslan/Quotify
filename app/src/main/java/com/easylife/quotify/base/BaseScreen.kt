package com.easylife.quotify.base

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import java.lang.annotation.Inherited

abstract class BaseScreen<VM: BaseViewModel, AC: BaseActions>: KoinComponent {

    //protected val analyticsManager: AnalyticsManager by inject()
    protected lateinit var viewModel: VM
    protected lateinit var navController: NavController
    protected lateinit var focusManager: FocusManager
    protected lateinit var navigationActions: AC

    @Composable
    fun Create(viewModel: VM, navController: NavController, navigationActions: AC) {
        this@BaseScreen.viewModel = viewModel
        this@BaseScreen.navController = navController
        this@BaseScreen.focusManager = LocalFocusManager.current
        this@BaseScreen.navigationActions = navigationActions

        val error by viewModel.error.observeAsState()
        if (error != null) {
            Log.d("ErrorControl", "==> $error")
        }

        val showProgress by viewModel.showProgress.observeAsState()
        if (showProgress == true) {
            Log.d("ProgressControl", "==> $showProgress")
        }

        Content()
    }

    @Composable
    abstract fun Content()
}