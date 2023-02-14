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
import com.estarly.petadoptionapp.ui.breed.BreedViewModel
import com.estarly.petadoptionapp.ui.home.HomeScreen
import com.estarly.petadoptionapp.ui.home.HomeViewModel
import com.estarly.petadoptionapp.ui.model.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel  : HomeViewModel  by viewModels()
    private val breedViewModel : BreedViewModel by viewModels()

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
                        arguments = listOf(
                            navArgument(Route.ScreenBreed.argumentIdBreed)   { type = NavType.IntType },
                            navArgument(Route.ScreenBreed.argumentBreedName) { type = NavType.StringType },
                            navArgument(Route.ScreenBreed.argumentImage)     { type = NavType.StringType },
                        )
                    )
                    { backStackEntry ->
                        val idBreed   = backStackEntry.arguments?.getInt(Route.ScreenBreed.argumentIdBreed)!!
                        val breedName = backStackEntry.arguments?.getString(Route.ScreenBreed.argumentBreedName)!!
                        val image     = backStackEntry.arguments?.getString(Route.ScreenBreed.argumentImage)!!
                        breedViewModel.getPets(idBreed)
                        BreedScreen(idBreed =  idBreed, nameBreed = breedName, image = image,breedViewModel)
                    }
                }
            }
        }
    }

}


