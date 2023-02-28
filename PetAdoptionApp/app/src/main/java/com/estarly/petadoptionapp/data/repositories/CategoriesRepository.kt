package com.estarly.petadoptionapp.data.repositories

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.data.api.clients.CategoryClient
import com.estarly.petadoptionapp.data.api.response.CategoryResponse
import com.estarly.petadoptionapp.domain.model.CategoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository @Inject constructor(private val categoryClient: CategoryClient){
    suspend fun getCategories(): BaseResultRepository<List<CategoryModel>> {
        return try {
            val response = categoryClient.getCategories()
            if(response != null && response.isNotEmpty()){
                BaseResultRepository.Success(response.map { it.toData() })
            }else{
                BaseResultRepository.NullOrEmptyData
            }
        }catch (e:Exception){
            BaseResultRepository.Error(e)
        }
    }
}
fun CategoryResponse.toData() : CategoryModel =
    CategoryModel(
        nameTag = this.nameTag,
        id = this.id
    )

