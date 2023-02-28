package com.estarly.petadoptionapp.domain.model

import java.io.Serializable

data class BreedModel(val idBreed : Int, val breedName : String, val amount : Int, val image : String, val idCategory : Int) : Serializable