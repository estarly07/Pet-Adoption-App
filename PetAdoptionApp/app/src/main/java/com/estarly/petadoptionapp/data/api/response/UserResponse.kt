package com.estarly.petadoptionapp.data.api.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")    val id    : String,
    @SerializedName("name")  val name  : String,
    @SerializedName("email") val email : String,
) {
    fun toMap(): HashMap<String,*> = hashMapOf(
        "id"    to id,
        "name"  to name,
        "email" to email
    )

}
