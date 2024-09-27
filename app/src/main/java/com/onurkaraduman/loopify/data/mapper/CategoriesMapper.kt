package com.onurkaraduman.loopify.data.mapper

import com.onurkaraduman.loopify.data.remote.dto.categories.CategoriesRepsonseItem
import com.onurkaraduman.loopify.domain.model.categories.CategoriesModel

fun List<CategoriesRepsonseItem>.toCategoriesModel(): List<CategoriesModel> {
    return this.map { categoryItem ->
        CategoriesModel(
            name = categoryItem.name,
            endPoint = categoryItem.slug
        )
    }
}