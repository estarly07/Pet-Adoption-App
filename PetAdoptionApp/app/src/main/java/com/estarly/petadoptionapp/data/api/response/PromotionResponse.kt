package com.estarly.petadoptionapp.data.api.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PromotionResponse(
    @SerializedName("id")         val id         : Int,
    @SerializedName("title")      val title      : String,
    @SerializedName("percentage") val percentage : String,
    @SerializedName("image")      val image      : String,
    @SerializedName("code")       val code       : String
)
