package com.estarly.petadoptionapp.data.api.clients

import com.estarly.petadoptionapp.data.api.response.PromotionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface PromotionClient {
    @Headers("Content-Type: application/json")
    @GET("promotion.json")
    suspend fun getPromotion() : PromotionResponse?

}