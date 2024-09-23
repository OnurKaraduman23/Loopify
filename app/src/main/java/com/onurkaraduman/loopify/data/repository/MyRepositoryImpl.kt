package com.onurkaraduman.loopify.data.repository

import com.onurkaraduman.loopify.data.remote.dto.products.ProductsResponse
import com.onurkaraduman.loopify.data.service.MyApi
import com.onurkaraduman.loopify.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myApi: MyApi
) : MyRepository {

    override suspend fun getAllProducts(): ProductsResponse {
        return myApi.getAllProducts()
    }
}