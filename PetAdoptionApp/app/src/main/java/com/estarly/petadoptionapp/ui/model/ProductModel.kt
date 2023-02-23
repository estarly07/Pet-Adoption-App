package com.estarly.petadoptionapp.ui.model

import java.io.Serializable

data class ProductModel(
    val idProduct      : Int,
    val nameProduct    : String,
    val imageProduct   : String,
    val price          : Double,
    var nameTypeProduct: String ="",
    val idTypeProduct  : Int,
    val cant           : Int,
    val aboutProduct   : String,
    val images         : List<String>
) : Serializable
