package com.estarly.petadoptionapp.domain.categories

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.CategoriesRepository
import com.estarly.petadoptionapp.ui.model.CategoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCategoriesUseCase @Inject constructor(private val categoriesRepository : CategoriesRepository) {
    suspend operator  fun invoke() : BaseResultUseCase<List<CategoryModel>>{
        return try {
            val response = categoriesRepository.getCategories()
            when(response){
                is BaseResultRepository.Error -> BaseResultUseCase.Error(response.exception)
                BaseResultRepository.NullOrEmptyData -> TODO()
                is BaseResultRepository.Success -> BaseResultUseCase.Success(response.data)
            }
        }catch (e:Exception){
            BaseResultUseCase.Error(e)
        }
    }
}
