package com.estarly.petadoptionapp.ui.model

data class PetModel(
    val idPet   : Int,
    val namePet : String,
    val image   : String,
    val about   : String,
    val months  : Int,
    val address : String,
    val sex     : String,
    val amount  : Double,
    val adopted : Boolean
)
