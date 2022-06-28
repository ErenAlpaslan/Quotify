package com.easylife.quotify.ui.screens.theme

import androidx.navigation.NavController
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val themeSelectionModule = module {
    factory { ThemeSelectionScreen() }
    viewModel { ThemeSelectionViewModel() }
    factory { (navController: NavController) -> ThemeSelectionNavigationAction(navController = navController) }
}