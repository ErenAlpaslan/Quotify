package com.easylife.quotify.ui.screens.settings

import androidx.navigation.NavController
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingModule = module {
    factory { SettingScreen() }
    viewModel { SettingViewModel() }
    factory { (navController: NavController) -> SettingNavigationActions(navController = navController) }
}