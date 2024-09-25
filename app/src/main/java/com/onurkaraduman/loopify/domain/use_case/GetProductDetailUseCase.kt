package com.onurkaraduman.loopify.domain.use_case

import com.onurkaraduman.loopify.common.Resource
import com.onurkaraduman.loopify.data.mapper.toProductDetailsModel
import com.onurkaraduman.loopify.domain.model.details.ProductDetailsModel
import com.onurkaraduman.loopify.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val myRepository: MyRepository
) {
    operator fun invoke(id: Int): Flow<Resource<ProductDetailsModel>> = flow {
        try {
            emit(Resource.Loading<ProductDetailsModel>())
            val response = myRepository.getProductDetails(id)
            val productDetails = response.toProductDetailsModel()
            emit(Resource.Success<ProductDetailsModel>(productDetails))
        } catch (e: IOException) {
            emit(Resource.Error<ProductDetailsModel>("Couldn't reach server. Check your internet connection"))

        } catch (e: Exception) {
            emit(
                Resource.Error<ProductDetailsModel>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        }
    }
}