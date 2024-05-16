package com.d4enst.laba_novigation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.d4enst.laba_novigation.pages.MainPage
import com.d4enst.laba_novigation.view_models.MainViewModel

@Composable
fun NavContent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    NavHost(
        navController = navController,
        startDestination = Page.MAIN_PAGE,
        modifier = modifier,
    ){

        composable(Page.MAIN_PAGE){
            val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
            mainViewModel.initializeFusedLocationClient(LocalContext.current)
            MainPage(
                navController,
                Modifier.fillMaxSize(),
                mainViewModel
            )
        }
    }
}