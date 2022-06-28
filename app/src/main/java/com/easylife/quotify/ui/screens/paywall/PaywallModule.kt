package com.easylife.quotify.ui.screens.paywall

import androidx.navigation.NavController
import com.easylife.quotify.ui.screens.theme.ThemeSelectionNavigationAction
import com.easylife.quotify.ui.screens.theme.ThemeSelectionScreen
import com.easylife.quotify.ui.screens.theme.ThemeSelectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val paywallModule = module {
    factory { PaywallScreen() }
    viewModel { PaywallViewModel() }
    factory { (navController: NavController) -> PaywallNavigationActions(navController = navController) }
}