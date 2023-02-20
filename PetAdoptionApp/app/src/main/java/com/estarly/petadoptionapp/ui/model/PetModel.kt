package com.estarly.petadoptionapp.ui.model

import com.google.gson.Gson
import java.io.Serializable
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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
fun PetModel.toJson(): String {
    return Gson().toJson(this.copy( address = URLEncoder.encode(address, StandardCharsets.UTF_8.toString()), image = URLEncoder.encode(image, StandardCharsets.UTF_8.toString())) )
}
fun String.toPetModel(): PetModel{
    val pet =Gson().fromJson(this, PetModel::class.java)
    return pet.copy (address = URLDecoder.decode(pet.address, StandardCharsets.UTF_8.toString()))
}
