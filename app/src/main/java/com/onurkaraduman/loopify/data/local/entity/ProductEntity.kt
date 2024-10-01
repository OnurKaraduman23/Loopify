package com.onurkaraduman.loopify.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Int,
    val category: String,
    val image: String
)
