package com.estarly.petadoptionapp.data.api.response

data class PaymentResponse(
    val id     : Int,
    val type   : Int,
    val number : String,
){
    companion object{
        fun fromJson(json : Map<String,*>) : PaymentResponse{
            return PaymentResponse(
                id      = json["id"].toString().toInt(),
                type    = json["type"].toString().toInt(),
                number  = json["number"].toString()
            )
        }
    }
}
