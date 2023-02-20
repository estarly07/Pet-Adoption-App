package com.estarly.petadoptionapp.data.api.clients

import com.estarly.petadoptionapp.data.api.response.ProductResponse
import com.estarly.petadoptionapp.data.api.response.TypeProductResponse
import retrofit2.http.GET

interface ProductsClient {
    @GET("typeProducts.json")
    suspend fun getTypeProducts() : List<TypeProductResponse>?
    @GET("products.json")
    suspend fun getProducts() : List<ProductResponse>?
}