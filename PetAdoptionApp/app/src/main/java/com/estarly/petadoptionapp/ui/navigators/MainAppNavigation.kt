package com.estarly.petadoptionapp.ui.navigators

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.estarly.petadoptionapp.ui.home.HomeScreen
import com.estarly.petadoptionapp.ui.home.HomeViewModel
import com.estarly.petadoptionapp.ui.store.StoreScreen

@Composable
fun MainAppNavigation(homeViewModel: HomeViewModel, navController : NavHostController){
    NavHost(
        navController = navController,
        startDestination = MainRoute.HomeScreen.route
    ){
        composable(MainRoute.HomeScreen.route){
            HomeScreen(homeViewModel = homeViewModel)
        }
        composable(MainRoute.StoreScreen.route){
            StoreScreen()
        }
    }
}

sealed class MainRoute(val route: String){
    object HomeScreen  : MainRoute("screenHome")
    object StoreScreen : MainRoute("screenStore")
}