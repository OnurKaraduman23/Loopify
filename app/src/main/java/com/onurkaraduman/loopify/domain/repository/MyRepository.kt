package com.onurkaraduman.loopify.domain.repository

import com.onurkaraduman.loopify.data.local.entity.ProductEntity
import com.onurkaraduman.loopify.data.remote.dto.categories.CategoriesRepsonseItem
import com.onurkaraduman.loopify.data.remote.dto.category_product.CategoryProductResponse
import com.onurkaraduman.loopify.data.remote.dto.detail.ProductDetailResponse
import com.onurkaraduman.loopify.data.remote.dto.products.ProductsResponse
import com.onurkaraduman.loopify.data.remote.dto.search.ProductsSearchResponse

interface MyRepository {
    // remote
    suspend fun getAllProducts(): ProductsResponse
    suspend fun getProductDetails(id: Int): ProductDetailResponse
    suspend fun searchProducts(searchQuery: String): ProductsSearchResponse
    suspend fun getCategories(): List<CategoriesRepsonseItem>
    suspend fun getCategoriesProducts(endPoint: String): CategoryProductResponse

    // local
    suspend fun upsertProduct(product: ProductEntity)
    suspend fun deleteProduct(product: ProductEntity)
    suspend fun selectProducts(): List<ProductEntity>
    suspend fun selectProduct(id: Int): ProductEntity?
    suspend fun isProductFavorite(id: Int): Boolean

}