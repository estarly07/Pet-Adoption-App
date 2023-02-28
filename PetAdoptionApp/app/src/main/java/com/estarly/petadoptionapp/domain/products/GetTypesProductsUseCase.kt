package com.estarly.petadoptionapp.domain.products

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.ProductsRepository
import com.estarly.petadoptionapp.domain.model.TypeProductModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTypesProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke() : BaseResultUseCase<List<TypeProductModel>>{
        return try {
            val response = productsRepository.getTypesProducts()
            when(response){
                is BaseResultRepository.Success -> BaseResultUseCase.Success(response.data)
                is BaseResultRepository.Error -> BaseResultUseCase.Error(response.exception)
                BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
            }

        }catch (e : Exception){
            BaseResultUseCase.Error(e)
        }
    }
}