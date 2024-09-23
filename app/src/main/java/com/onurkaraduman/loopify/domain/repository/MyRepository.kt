package com.onurkaraduman.loopify.domain.repository

import com.onurkaraduman.loopify.data.remote.dto.products.ProductsResponse

interface MyRepository{
    suspend fun getAllProducts(): ProductsResponse
}