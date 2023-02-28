package com.estarly.petadoptionapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.estarly.petadoptionapp.data.database.dao.UserDao
import com.estarly.petadoptionapp.data.database.entities.UserEntity

@Database(
    version = 1,
    entities = [
        UserEntity::class
    ]
)
abstract class PetDatabase : RoomDatabase(){
    abstract fun getUserDao() : UserDao
}