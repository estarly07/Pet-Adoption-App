package com.estarly.petadoptionapp.ui.navigator

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Route(val route : String){
    object ScreenHome  : Route("screenHome")
    object ScreenBreed : Route("screenBreed/{idBreed}/{breedName}/{image}"){
        const val argumentIdBreed   = "idBreed"
        const val argumentBreedName = "breedName"
        const val argumentImage     = "image"
        fun createRoute(idBreed : Int, breedName : String, image:String) = "screenBreed/$idBreed/$breedName/${URLEncoder.encode(image, StandardCharsets.UTF_8.toString())}"
    }
    object ScreenPet : Route("screenPet/{breedName}"){
        const val argumentBreedName = "breedName"
        fun createRoute(breedName : String) = "screenPet/$breedName"
    }
}
