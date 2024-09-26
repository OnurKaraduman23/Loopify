package com.onurkaraduman.loopify.data.repository

import com.onurkaraduman.loopify.data.remote.dto.detail.ProductDetailResponse
import com.onurkaraduman.loopify.data.remote.dto.products.ProductsResponse
import com.onurkaraduman.loopify.data.remote.dto.search.ProductsSearchResponse
import com.onurkaraduman.loopify.data.service.MyApi
import com.onurkaraduman.loopify.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myApi: MyApi
) : MyRepository {

    override suspend fun getAllProducts(): ProductsResponse {
        return myApi.getAllProducts()
    }

    override suspend fun getProductDetails(id: Int): ProductDetailResponse {
        return myApi.getProductDetails(id)
    }

    override suspend fun searchProducts(searchQuery: String): ProductsSearchResponse {
        return myApi.searchProduct(searchQuery)
    }
}