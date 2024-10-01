package com.onurkaraduman.loopify.domain.use_case.local

import com.onurkaraduman.loopify.data.local.entity.ProductEntity
import com.onurkaraduman.loopify.domain.repository.MyRepository

class SelectProduct(
    private val myRepository: MyRepository
) {

    suspend operator fun invoke(id: Int): ProductEntity? {
        return myRepository.selectProduct(id)
    }

}