package com.onurkaraduman.loopify.domain.use_case.local

import com.onurkaraduman.loopify.data.local.entity.ProductEntity
import com.onurkaraduman.loopify.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SelectProducts(
    private val myRepository: MyRepository
) {

    operator fun invoke(): Flow<List<ProductEntity>> = flow {
        val response = myRepository.selectProducts()
        emit(response)
    }
}