package com.onurkaraduman.loopify.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onurkaraduman.loopify.data.local.dao.ProductDao
import com.onurkaraduman.loopify.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 5)
abstract class ProductsDatabase : RoomDatabase() {
    abstract val productDao: ProductDao
}