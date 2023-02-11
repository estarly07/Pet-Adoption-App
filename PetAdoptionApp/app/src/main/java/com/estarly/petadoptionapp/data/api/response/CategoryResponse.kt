package com.estarly.petadoptionapp.data.api.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse (
    @SerializedName("id") val id :Int,
    @SerializedName("name") val nameTag : String)
