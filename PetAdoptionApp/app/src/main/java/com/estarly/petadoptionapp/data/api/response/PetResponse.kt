package com.estarly.petadoptionapp.data.api.response

import com.google.gson.annotations.SerializedName

data class PetResponse (
    @SerializedName("idPet")   val idPet   : Int,
    @SerializedName("namePet") val namePet : String,
    @SerializedName("image")   val image   : String,
    @SerializedName("about")   val about   : String,
    @SerializedName("months")  val months  : Int,
    @SerializedName("address") val address : String,
    @SerializedName("sex")     val sex     : String,
    @SerializedName("amount")  val amount  : Double,
    @SerializedName("adopted") val adopted : Boolean
)