package com.estarly.petadoptionapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.estarly.petadoptionapp.ui.breed.BreedScreen
import com.estarly.petadoptionapp.ui.home.HomeScreen
import com.estarly.petadoptionapp.ui.home.HomeViewModel
import com.estarly.petadoptionapp.ui.model.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel : HomeViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.onCreate()
        setContent {
            val navigationController = rememberNavController()
            Scaffold {
                NavHost(
                    navController = navigationController,
                    startDestination = Route.ScreenHome.route
                ) {
                    composable(Route.ScreenHome.route) { HomeScreen(homeViewModel, navigationController) }
                    composable(
                        Route.ScreenBreed.route,
                        arguments = listOf(navArgument("idBreed") { type = NavType.IntType })
                    )
                    { backStackEntry ->
                        backStackEntry.arguments?.getInt("idBreed")?.let {idBreed->
                            BreedScreen(idBreed)
                        }
                    }
                }
            }
        }
    }

}


