package com.estarly.petadoptionapp.di

import android.content.Context
import androidx.room.Room
import com.estarly.petadoptionapp.data.database.PetDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context)
            = Room.databaseBuilder(context, PetDatabase::class.java,"PET_DATABASE").build()

    @Singleton
    @Provides
    fun provideUserDao(db: PetDatabase)  = db.getUserDao()
}