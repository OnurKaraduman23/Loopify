package com.onurkaraduman.loopify.domain.use_case

import com.onurkaraduman.loopify.common.Resource
import com.onurkaraduman.loopify.data.mapper.toCategoriesModel
import com.onurkaraduman.loopify.domain.model.categories.CategoriesModel
import com.onurkaraduman.loopify.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val myRepository: MyRepository
) {
    operator fun invoke(): Flow<Resource<List<CategoriesModel>>> = flow {
        try {
            emit(Resource.Loading<List<CategoriesModel>>())
            val response = myRepository.getCategories()
            val category = response.toCategoriesModel()
            emit(Resource.Success<List<CategoriesModel>>(category))
        } catch (e: IOException) {
            emit(Resource.Error<List<CategoriesModel>>("Couldn't reach server. Check your internet connection"))
        } catch (e: Exception) {
            emit(
                Resource.Error<List<CategoriesModel>>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        }
    }
}