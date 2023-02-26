package com.estarly.petadoptionapp.di

import android.content.Context
import com.estarly.petadoptionapp.data.api.clients.*
import com.estarly.petadoptionapp.data.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun providePetClient(retrofit: Retrofit) : PetClient = retrofit.create(PetClient::class.java)

    @Singleton
    @Provides
    fun provideProductsClient(retrofit: Retrofit) : ProductsClient = retrofit.create(ProductsClient::class.java)

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext appContext: Context) : Preferences = Preferences(context = appContext)
}