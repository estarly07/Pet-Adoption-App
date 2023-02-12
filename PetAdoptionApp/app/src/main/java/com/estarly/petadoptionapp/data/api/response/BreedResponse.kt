package com.estarly.petadoptionapp.data.api.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class BreedResponse (
    @SerializedName("breedName") val breedName  : String,
    @SerializedName("amount")    val amount     : Int,
    @SerializedName("image")     val image      : String,
    @SerializedName("idCategory")val idCategory : Int,
    @SerializedName("idBreed")   val idBreed    : Int
)
