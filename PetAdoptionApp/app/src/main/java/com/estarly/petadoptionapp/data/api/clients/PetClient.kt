package com.estarly.petadoptionapp.data.api.clients

import com.estarly.petadoptionapp.data.api.response.PetResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PetClient {
    @GET("breeds/{idBreed}/pets.json")
    suspend fun getPets(@Path("idBreed") idBreed : Int) : List<PetResponse>?
}
