package com.estarly.petadoptionapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")    val id    : String,
    @ColumnInfo(name = "name")  val name  : String,
    @ColumnInfo(name = "email") val email : String,
)
