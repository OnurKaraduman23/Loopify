package com.onurkaraduman.loopify.data.service

import com.onurkaraduman.loopify.data.remote.dto.detail.ProductDetailResponse
import com.onurkaraduman.loopify.data.remote.dto.products.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MyApi {

    @GET("products")
    suspend fun getAllProducts(): ProductsResponse

    @GET("products/{id}")
    suspend fun getProductDetails(
        @Path("id") id: Int
    ): ProductDetailResponse
}