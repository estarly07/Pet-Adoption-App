package com.estarly.petadoptionapp.data.api.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("idProduct")     val idProduct      : Int,
    @SerializedName("nameProduct")   val nameProduct    : String,
    @SerializedName("imageProduct")  val imageProduct   : String,
    @SerializedName("price")         val price          : Double,
    @SerializedName("idTypeProduct") val idTypeProduct  : Int,
    @SerializedName("cant")          val cant           : Int,
    @SerializedName("aboutProduct")  val aboutProduct   : String,
    @SerializedName("images")        val images         : List<String>
){
    fun toJson(): Map<String,*> =
        mapOf(
            "idProduct" to idProduct,
            "nameProduct" to nameProduct,
            "imageProduct" to imageProduct,
            "price" to price,
            "idTypeProduct" to idTypeProduct,
            "cant" to cant,
            "aboutProduct" to aboutProduct,
            "images" to images,
        )

    companion object{
        fun fromJson(json :Map<String,*>) : ProductResponse {
            return  ProductResponse(
                (json["idProduct"]as Long).toInt(),
                json["nameProduct"] as String,
                json["imageProduct"] as String,
                json["price"] as Double,
                (json["idTypeProduct"]as Long).toInt(),
                (json["cant"]as Long).toInt(),
                json["aboutProduct"] as String,
                json["images"] as List<String>,
            )
        }
    }
}

