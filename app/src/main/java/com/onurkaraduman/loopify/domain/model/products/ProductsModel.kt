package com.onurkaraduman.loopify.domain.model.products

data class ProductsModel(
    val id: Int,
    val title: String,
    val price: Int,
    val category: String,
    val tags: List<String>,
    val rating:Int,
    val image: String
)
