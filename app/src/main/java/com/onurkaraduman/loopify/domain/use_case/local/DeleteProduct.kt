package com.onurkaraduman.loopify.domain.use_case.local

import com.onurkaraduman.loopify.data.local.entity.ProductEntity
import com.onurkaraduman.loopify.domain.repository.MyRepository

class DeleteProduct(
    private val myRepository: MyRepository
) {

    suspend operator fun invoke(product: ProductEntity) {
        myRepository.deleteProduct(product)
    }
}