package com.estarly.petadoptionapp.ui.model

data class ProductModel(
    val idProduct      : Int,
    val nameProduct    : String,
    val imageProduct   : String,
    val price          : Double,
    var nameTypeProduct: String ="",
    val idTypeProduct  : Int,
    val cant           : Int,
    val aboutProduct   : String
)
