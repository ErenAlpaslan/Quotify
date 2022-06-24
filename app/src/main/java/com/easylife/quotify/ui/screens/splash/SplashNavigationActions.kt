package com.easylife.quotify.ui.screens.splash

import androidx.navigation.NavController
import com.easylife.quotify.base.BaseActions
import com.easylife.quotify.ui.navigation.Screen

class SplashNavigationActions(
    navController: NavController
): BaseActions() {
    val navigateToHome: () -> Unit = {
        navController.navigate(Screen.Home.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }

    val navigateToOnBoarding: () -> Unit = {
        navController.navigate(Screen.OnBoarding.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }

            launchSingleTop = true
        }
    }
}