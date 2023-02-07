package com.estarly.petadoptionapp.domain.promotion.model

data class PromotionResponse(
    val id: Int,
    val title: String,
    val percentage: String,
    val image: String,
    val code: String
)
