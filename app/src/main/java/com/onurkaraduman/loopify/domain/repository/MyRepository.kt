package com.onurkaraduman.loopify.domain.repository

import com.onurkaraduman.loopify.data.remote.dto.detail.ProductDetailResponse
import com.onurkaraduman.loopify.data.remote.dto.products.ProductsResponse
import com.onurkaraduman.loopify.data.remote.dto.search.ProductsSearchResponse

interface MyRepository{
    suspend fun getAllProducts(): ProductsResponse
    suspend fun getProductDetails(id: Int): ProductDetailResponse
    suspend fun searchProducts(searchQuery: String): ProductsSearchResponse
}