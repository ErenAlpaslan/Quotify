package com.easylife.quotify.ui.screens.home

import androidx.navigation.NavController
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory { HomeScreen() }
    viewModel { HomeViewModel(get()) }
    factory { (navController: NavController) -> HomeNavigationActions(navController = navController) }
}