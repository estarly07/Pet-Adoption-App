package com.estarly.petadoptionapp.ui.navigators

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.estarly.petadoptionapp.ui.breed.BreedScreen
import com.estarly.petadoptionapp.ui.breed.BreedViewModel
import com.estarly.petadoptionapp.ui.model.BreedModel
import com.estarly.petadoptionapp.ui.model.toPetModel
import com.estarly.petadoptionapp.ui.pet.PetScreen
import com.estarly.petadoptionapp.ui.pet.PetViewModel

@Composable
fun BreedNavigation(
    breed : BreedModel,
    breedViewModel: BreedViewModel,
    petViewModel: PetViewModel
) {
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = Route.ScreenBreed.route
    ) {
        composable( Route.ScreenBreed.route,){
            BreedScreen(nameBreed = breed.breedName, image = breed.image,breedViewModel,navigationController)
        }
        composable(Route.ScreenPet.route){ backStackEntry ->
            val breedName = backStackEntry.arguments?.getString(Route.ScreenPet.argumentBreedName)!!
            val pet       = backStackEntry.arguments?.getString(Route.ScreenPet.argumentPet)!!
            Log.i("PET",pet)
            PetScreen(pet.toPetModel(), breedName, petViewModel,navigationController)
        }
    }
}
sealed class Route(val route : String){
    object ScreenBreed : Route("screenBreed")
    object ScreenPet : Route("screenPet/{breedName}/{pet}"){
        const val argumentBreedName = "breedName"
        const val argumentPet       = "pet"
        fun createRoute(breedName : String,pet : String) = "screenPet/$breedName/$pet"
    }
}
