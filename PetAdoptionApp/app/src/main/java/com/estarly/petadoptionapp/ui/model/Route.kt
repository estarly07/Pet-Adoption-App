package com.estarly.petadoptionapp.ui.model

sealed class Route(val route : String){
    object ScreenHome  : Route("screenHome")
    object ScreenBreed : Route("screenBreed/{idBreed}"){
        fun createRoute(idBreed : Int) = "screenBreed/$idBreed"
    }
}
