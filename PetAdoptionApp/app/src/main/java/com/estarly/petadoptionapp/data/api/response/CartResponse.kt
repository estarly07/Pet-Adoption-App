package com.estarly.petadoptionapp.data.api.response

data class CartResponse(
    val idCart : String,
    val list : List<ProductCartResponse>
) {
    fun toJson(): Map<String,*> =
        mapOf(
            "idCart" to idCart,
            "list" to list.map { it.toJson() }.toList(),
    )

    companion object{
        fun fromJson(json: Map<String, *>): CartResponse {
            return CartResponse(
                idCart = json["idCart"] as String,
                list = (json["list"] as MutableList<*>).map { ProductCartResponse.fromJson(it as Map<String, *>) }.toMutableList()
            )
        }
    }
}
