package com.easylife.quotify.ui.screens.onboarding

import androidx.navigation.NavController
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val onBoardingModule = module {
    factory { OnBoardingScreen() }
    viewModel { OnBoardingViewModel(get()) }
    factory {(navController: NavController) -> OnBoardingNavigationActions(navController) }
}