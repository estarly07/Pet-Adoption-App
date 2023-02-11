package com.estarly.petadoptionapp.di

import com.estarly.petadoptionapp.data.api.clients.BreedClient
import com.estarly.petadoptionapp.data.api.clients.CategoryClient
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

    @Singleton
    @Provides
    fun provideBreedClient(retrofit: Retrofit) : BreedClient = retrofit.create(BreedClient::class.java)

    @Singleton
    @Provides
    fun provideCategoryClient(retrofit: Retrofit): CategoryClient = retrofit.create(CategoryClient::class.java)
}