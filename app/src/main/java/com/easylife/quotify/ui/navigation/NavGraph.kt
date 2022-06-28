package com.easylife.quotify.ui.navigation

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.easylife.quotify.ui.screens.favorites.FavoriteScreen
import com.google.accompanist.navigation.animation.composable
import com.easylife.quotify.ui.screens.home.HomeScreen
import com.easylife.quotify.ui.screens.onboarding.OnBoardingScreen
import com.easylife.quotify.ui.screens.paywall.PaywallScreen
import com.easylife.quotify.ui.screens.settings.SettingScreen
import com.easylife.quotify.ui.screens.splash.SplashScreen
import com.easylife.quotify.ui.screens.theme.ThemeSelectionScreen
import com.easylife.quotify.ui.view.EnterAnimation
import com.google.accompanist.navigation.animation.AnimatedNavHost
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(navController: NavHostController) {
    AnimatedNavHost(
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

        composable(
            route = Screen.Theme.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(700))
            },
            popEnterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(700))
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(700))
            }
        ) {
            get<ThemeSelectionScreen>().Create(
                viewModel = getViewModel(),
                navController = navController,
                navigationActions = get { parametersOf(navController) }
            )
        }

        composable(
            route = Screen.Setting.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(700))
            },
            popEnterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(700))
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(700))
            }
        ) {
            get<SettingScreen>().Create(
                viewModel = getViewModel(),
                navController = navController,
                navigationActions = get { parametersOf(navController) }
            )
        }

        composable(
            route = Screen.Paywall.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(700))
            },
            popEnterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(700))
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(700))
            }
        ) {
            get<PaywallScreen>().Create(
                viewModel = getViewModel(),
                navController = navController,
                navigationActions = get { parametersOf(navController) }
            )
        }

        composable(
            route = Screen.Favorite.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Up, animationSpec = tween(700))
            },
            popEnterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(700))
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Down, animationSpec = tween(700))
            }
        ) {
            get<FavoriteScreen>().Create(
                viewModel = getViewModel(),
                navController = navController,
                navigationActions = get { parametersOf(navController) }
            )
        }
    }
}