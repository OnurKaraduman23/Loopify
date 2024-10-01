package com.onurkaraduman.loopify.domain.use_case.local

data class LocalProductUseCase(
    val upsertProduct: UpsertProduct,
    val selectProduct: SelectProduct,
    val deleteProduct: DeleteProduct,
    val selectProducts: SelectProducts,
    val isFavoriteProduct: IsFavoriteProduct
)
