package com.estarly.petadoptionapp.ui.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.estarly.petadoptionapp.ui.breed.BreedScreen
import com.estarly.petadoptionapp.ui.breed.BreedViewModel
import com.estarly.petadoptionapp.ui.home.HomeScreen
import com.estarly.petadoptionapp.ui.home.HomeViewModel
import com.estarly.petadoptionapp.ui.model.PetModel
import com.estarly.petadoptionapp.ui.pet.PetScreen
import com.estarly.petadoptionapp.ui.pet.PetViewModel

@Composable
fun AppNavigation(
    homeViewModel: HomeViewModel,
    breedViewModel: BreedViewModel,
    petViewModel: PetViewModel
) {
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = Route.ScreenHome.route
    ) {
        composable(Route.ScreenHome.route) { HomeScreen(homeViewModel,breedViewModel, navigationController) }
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
            BreedScreen(idBreed =  idBreed, nameBreed = breedName, image = image,breedViewModel,navigationController)
        }
        composable(Route.ScreenPet.route){ backStackEntry ->
            navigationController.previousBackStackEntry?.arguments?.let {
                val breedName = backStackEntry.arguments?.getString(Route.ScreenPet.argumentBreedName)!!
                PetScreen(it.getSerializable("pet") as PetModel, breedName, petViewModel,navigationController)
            }
        }
    }
}