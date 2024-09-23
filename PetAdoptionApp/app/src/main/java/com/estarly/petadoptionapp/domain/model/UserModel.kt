package com.estarly.petadoptionapp.domain.model

data class UserModel(
    val id    : String,
    val name  : String,
    val email : String,
    val avatar: String = "https://images.unsplash.com/photo-1618641986557-1ecd230959aa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8cHJvZmlsZXxlbnwwfHwwfHw%3D&w=1000&q=80",
)
