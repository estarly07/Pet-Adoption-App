package com.estarly.petadoptionapp.domain.model

data class PaymentModel(
    val id : Int,
    val type : PaymentsTypes,
    val number: String,
)
enum class PaymentsTypes {
    VISA,MASTERCARD
}
