package com.estarly.petadoptionapp.data.api.response

data class ProductCartResponse(
    val productResponse: ProductResponse,
    var cant : Int,
){
    fun toJson() : Map<String,*>{
        return mapOf(
            "productResponse" to productResponse.toJson(),
            "cant" to cant,
        )
    }

    companion object{
        fun fromJson(json :Map<String,*>) : ProductCartResponse {
            return  ProductCartResponse(
                cant = (json["cant"] as Long).toInt(),
                productResponse = ProductResponse.fromJson(json["productResponse"] as Map<String, *>)
            )
        }
    }
}
