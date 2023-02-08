package com.estarly.petadoptionapp.data.api.clients

import com.estarly.petadoptionapp.data.api.response.BreedResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface BreedClient {
    @Headers("Content-Type: application/json")
    @GET("breeds.json")
    suspend fun getBreeds() : List<BreedResponse>?
}
