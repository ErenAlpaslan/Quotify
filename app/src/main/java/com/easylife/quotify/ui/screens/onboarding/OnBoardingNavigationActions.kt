package com.easylife.quotify.ui.screens.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.easylife.quotify.base.BaseActions
import com.easylife.quotify.ui.navigation.Screen

class OnBoardingNavigationActions(
    navController: NavController
): BaseActions() {
    val navigateToHome: () -> Unit = {
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.OnBoarding.route) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    val navigateToPaywall: () -> Unit = {
        /*TODO: Create Paywall */
    }
}