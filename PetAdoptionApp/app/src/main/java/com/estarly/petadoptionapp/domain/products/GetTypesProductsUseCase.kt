package com.estarly.petadoptionapp.domain.products

import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.ui.model.TypeProductModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTypesProductsUseCase @Inject constructor() {
    suspend operator fun invoke() : BaseResultUseCase<List<TypeProductModel>>{
        return try {
            BaseResultUseCase.Success(listOf(
                TypeProductModel(0,"type 1"),
                TypeProductModel(1,"type 2"),
            ))
        }catch (e : Exception){
            BaseResultUseCase.Error(e)
        }
    }
}