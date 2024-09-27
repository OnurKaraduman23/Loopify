package com.onurkaraduman.loopify.data.service

import com.onurkaraduman.loopify.data.remote.dto.categories.CategoriesRepsonseItem
import com.onurkaraduman.loopify.data.remote.dto.category_product.CategoryProductResponse
import com.onurkaraduman.loopify.data.remote.dto.detail.ProductDetailResponse
import com.onurkaraduman.loopify.data.remote.dto.products.ProductsResponse
import com.onurkaraduman.loopify.data.remote.dto.search.ProductsSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApi {

    @GET("products")
    suspend fun getAllProducts(): ProductsResponse

    @GET("products/{id}")
    suspend fun getProductDetails(
        @Path("id") id: Int
    ): ProductDetailResponse

    @GET("products/search")
    suspend fun searchProduct(
        @Query("q") searchQuery: String
    ): ProductsSearchResponse

    @GET("products/categories")
    suspend fun getCategories(): List<CategoriesRepsonseItem>

    @GET("products/category/{end_point}")
    suspend fun getCategoriesProducts(
        @Path("end_point") endPoint: String
    ): CategoryProductResponse

}