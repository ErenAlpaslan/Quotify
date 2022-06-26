package com.easylife.quotify.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.easylife.quotify.ui.screens.home.HomeScreen
import com.easylife.quotify.ui.screens.onboarding.OnBoardingScreen
import com.easylife.quotify.ui.screens.splash.SplashScreen
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            get<SplashScreen>().Create(
                viewModel = getViewModel(),
                navController = navController,
                navigationActions = get { parametersOf(navController) }
            )
        }

        composable(route = Screen.OnBoarding.route) {
            get<OnBoardingScreen>().Create(
                viewModel = getViewModel(),
                navController = navController,
                navigationActions = get { parametersOf(navController) }
            )
        }

        composable(route = Screen.Home.route) {
            get<HomeScreen>().Create(
                viewModel = getViewModel(),
                navController = navController,
                navigationActions = get { parametersOf(navController) }
            )
        }
    }
}