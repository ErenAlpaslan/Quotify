package com.easylife.quotify.ui.screens.settings

import androidx.navigation.NavController
import com.easylife.quotify.base.BaseActions
import com.easylife.quotify.ui.navigation.Screen

class SettingNavigationActions(navController: NavController): BaseActions() {
    val navigateToPayWall: () -> Unit = {
        navController.navigate(Screen.Paywall.route)
    }

    val navigateToFavorites: () -> Unit = {
        navController.navigate(Screen.Favorite.route)
    }
}