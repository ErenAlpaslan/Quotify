package com.easylife.quotify.ui.screens.home

import androidx.navigation.NavController
import com.easylife.quotify.base.BaseActions
import com.easylife.quotify.ui.navigation.Screen

class HomeNavigationActions(
    navController: NavController
): BaseActions() {

    val navigateToThemeSelection: () -> Unit = {
        navController.navigate(Screen.Theme.route)
    }

    val navigateToPaywall: () -> Unit = {
        navController.navigate(Screen.Paywall.route)
    }

    val navigateToSettings: () -> Unit = {
        navController.navigate(Screen.Setting.route)
    }

    val navigateToFavorites: () -> Unit = {
        navController.navigate(Screen.Favorite.route)
    }

}