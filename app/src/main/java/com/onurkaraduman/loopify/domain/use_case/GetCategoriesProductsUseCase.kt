package com.onurkaraduman.loopify.domain.use_case

import com.onurkaraduman.loopify.common.Resource
import com.onurkaraduman.loopify.data.mapper.toProductModel
import com.onurkaraduman.loopify.domain.model.products.ProductsModel
import com.onurkaraduman.loopify.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCategoriesProductsUseCase @Inject constructor(
    private val myRepository: MyRepository
) {
    operator fun invoke(endPoint: String): Flow<Resource<List<ProductsModel>>> = flow {
        try {
            emit(Resource.Loading<List<ProductsModel>>())
            val response = myRepository.getCategoriesProducts(endPoint)
            val products = response.toProductModel()
            emit(Resource.Success<List<ProductsModel>>(products))
        } catch (e: IOException) {
            emit(Resource.Error<List<ProductsModel>>("Couldn't reach server. Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error<List<ProductsModel>>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}