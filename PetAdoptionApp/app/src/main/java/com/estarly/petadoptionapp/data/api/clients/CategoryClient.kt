package com.estarly.petadoptionapp.data.api.clients

import com.estarly.petadoptionapp.data.api.response.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface CategoryClient {
    @Headers("Content-Type: application/json")
    @GET("categories.json")
    suspend fun getCategories() : List<CategoryResponse>?

}
