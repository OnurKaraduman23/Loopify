package com.onurkaraduman.loopify.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.onurkaraduman.loopify.data.local.entity.ProductEntity

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(product: ProductEntity)

    @Delete
    suspend fun delete(product: ProductEntity)

    @Query("SELECT * FROM ProductEntity")
    fun getProducts(): List<ProductEntity>

    @Query("SELECT * FROM ProductEntity WHERE id=:id")
    suspend fun getProduct(id: Int): ProductEntity?

    @Query("SELECT COUNT(*) > 0 FROM ProductEntity WHERE id = :id")
    suspend fun isProductFavorite(id: Int): Boolean
}