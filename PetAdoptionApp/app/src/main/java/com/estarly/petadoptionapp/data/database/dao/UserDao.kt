package com.estarly.petadoptionapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.estarly.petadoptionapp.data.database.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM USER")
    suspend fun getUser() : UserEntity
    @Insert
    suspend fun insertUser(userEntity: UserEntity)
}