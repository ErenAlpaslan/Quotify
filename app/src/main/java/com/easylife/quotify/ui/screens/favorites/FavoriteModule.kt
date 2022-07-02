package com.easylife.quotify.ui.screens.favorites

import androidx.navigation.NavController
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    factory { FavoriteScreen() }
    viewModel { FavoriteViewModel(get(), get()) }
    factory {(navController: NavController) -> FavoriteNavigationActions(navController) }
}