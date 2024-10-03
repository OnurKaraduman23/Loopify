package com.onurkaraduman.loopify.data.repository.impl

import com.onurkaraduman.loopify.data.local.dao.ProductDao
import com.onurkaraduman.loopify.data.local.entity.ProductEntity
import com.onurkaraduman.loopify.data.remote.dto.categories.CategoriesRepsonseItem
import com.onurkaraduman.loopify.data.remote.dto.category_product.CategoryProductResponse
import com.onurkaraduman.loopify.data.remote.dto.detail.ProductDetailResponse
import com.onurkaraduman.loopify.data.remote.dto.products.ProductsResponse
import com.onurkaraduman.loopify.data.remote.dto.search.ProductsSearchResponse
import com.onurkaraduman.loopify.data.service.MyApi
import com.onurkaraduman.loopify.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val myApi: MyApi,
    private val productDao: ProductDao
) : MyRepository {

    // Remote
    override suspend fun getAllProducts(): ProductsResponse {
        return myApi.getAllProducts()
    }

    override suspend fun getProductDetails(id: Int): ProductDetailResponse {
        return myApi.getProductDetails(id)
    }

    override suspend fun searchProducts(searchQuery: String): ProductsSearchResponse {
        return myApi.searchProduct(searchQuery)
    }

    override suspend fun getCategories(): List<CategoriesRepsonseItem> {
        return myApi.getCategories()
    }

    override suspend fun getCategoriesProducts(endPoint: String): CategoryProductResponse {
        return myApi.getCategoriesProducts(endPoint)
    }

    // Local

    override suspend fun upsertProduct(product: ProductEntity) {
        productDao.upsert(product)
    }

    override suspend fun deleteProduct(product: ProductEntity) {
        productDao.delete(product)
    }

    override suspend fun selectProducts(): List<ProductEntity> {
        return productDao.getProducts()
    }

    override suspend fun selectProduct(id: Int): ProductEntity? {
        return productDao.getProduct(id)
    }

    override suspend fun isProductFavorite(id: Int): Boolean {
        return productDao.isProductFavorite(id)
    }


}