package com.onurkaraduman.loopify.data.service

import com.onurkaraduman.loopify.data.remote.dto.products.ProductsResponse
import retrofit2.http.GET

interface MyApi {

    @GET("products")
    suspend fun getAllProducts(): ProductsResponse

}