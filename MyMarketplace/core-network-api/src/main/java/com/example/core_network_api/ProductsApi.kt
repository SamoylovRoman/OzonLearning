package com.example.core_network_api

import com.example.core_network_api.models.ProductDTO
import com.example.core_network_api.models.ProductInListDTO
import retrofit2.Call
import retrofit2.http.GET

interface ProductsApi {
    @GET("50afcd4b-d81e-473e-827c-1b6cae1ea1b2")
    fun getProductsInList(): Call<List<ProductInListDTO>>

    @GET("8c374376-e94e-4c5f-aa30-a9eddb0d7d0a")
    fun getProducts(): Call<List<ProductDTO>>
}