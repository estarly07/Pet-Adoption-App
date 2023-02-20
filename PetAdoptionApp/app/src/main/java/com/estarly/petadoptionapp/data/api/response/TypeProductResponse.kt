package com.estarly.petadoptionapp.data.api.response

import com.google.gson.annotations.SerializedName

data class TypeProductResponse(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String
)
