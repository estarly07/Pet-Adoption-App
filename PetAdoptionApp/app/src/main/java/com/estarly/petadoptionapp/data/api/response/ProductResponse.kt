package com.estarly.petadoptionapp.data.api.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("idProduct")     val idProduct      : Int,
    @SerializedName("nameProduct")   val nameProduct    : String,
    @SerializedName("imageProduct")  val imageProduct   : String,
    @SerializedName("price")         val price          : Double,
    @SerializedName("idTypeProduct") val idTypeProduct  : Int,
    @SerializedName("cant")          val cant           : Int,
    @SerializedName("aboutProduct")  val aboutProduct   : String
)

