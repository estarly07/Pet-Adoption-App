package com.estarly.petadoptionapp.domain.model

data class CartModel(
    val idCart : String,
    val list : MutableList<ProductCartModel>
)
