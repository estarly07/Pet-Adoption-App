package com.estarly.petadoptionapp.ui.store

data class ProductModel(
    val idProduct     : Int,
    val nameProduct   : String,
    val imageProduct  : String,
    val price         : Double,
    val idTypeProduct : Int,
    val cant          : Int,
    val aboutProduct  : String
)
