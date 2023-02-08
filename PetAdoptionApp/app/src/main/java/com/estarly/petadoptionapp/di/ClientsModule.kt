package com.estarly.petadoptionapp.di

import com.estarly.petadoptionapp.data.api.clients.PromotionClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ClientsModule {
    @Singleton
    @Provides
    fun providePromotionClient(retrofit: Retrofit) : PromotionClient = retrofit.create(PromotionClient::class.java)
}