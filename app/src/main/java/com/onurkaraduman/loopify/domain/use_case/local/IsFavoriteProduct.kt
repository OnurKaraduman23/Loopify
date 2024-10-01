package com.onurkaraduman.loopify.domain.use_case.local

import com.onurkaraduman.loopify.domain.repository.MyRepository
import javax.inject.Inject

class IsFavoriteProduct @Inject constructor(
    private val myRepository: MyRepository
) {
    suspend operator fun invoke(id: Int): Boolean = myRepository.isProductFavorite(id)
}