package com.estarly.petadoptionapp.domain.products

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.ProductsRepository
import com.estarly.petadoptionapp.ui.model.ProductModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetProductsUseCase @Inject constructor(
    private val getTypesProductsUseCase: GetTypesProductsUseCase,
    private val productsRepository : ProductsRepository
){
    suspend operator fun invoke() : BaseResultUseCase<List<ProductModel>>{
        return try {
            val responseTypes = getTypesProductsUseCase()
            when(responseTypes){
                is BaseResultUseCase.Success -> {
                    val responseProducts = productsRepository.getProducts()
                    when(responseProducts){
                        is BaseResultRepository.Success -> {
                            val list = responseProducts.data
                            list.forEach {
                                it.apply { nameTypeProduct = responseTypes.data.find {it.id == idTypeProduct}?.name ?: "" }
                            }
                            BaseResultUseCase.Success(list)
                        }
                        is BaseResultRepository.Error -> TODO()
                        BaseResultRepository.NullOrEmptyData -> TODO()
                    }

                }
                is BaseResultUseCase.Error -> TODO()
                BaseResultUseCase.NoInternetConnection -> TODO()
                BaseResultUseCase.NullOrEmptyData -> TODO()
            }
        }catch (e : Exception){
            BaseResultUseCase.Error(e)
        }
    }
}