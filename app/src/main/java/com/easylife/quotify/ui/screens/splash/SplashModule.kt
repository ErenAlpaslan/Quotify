package com.easylife.quotify.ui.screens.splash

import androidx.navigation.NavController
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    factory { SplashScreen() }
    viewModel { SplashViewModel(get()) }
    factory { (navController: NavController) -> SplashNavigationActions(navController) }
}