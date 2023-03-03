package com.estarly.petadoptionapp.domain.cart

import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.model.ProductCartModel
import com.estarly.petadoptionapp.domain.model.ProductModel
import com.estarly.petadoptionapp.domain.user.GetUserUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetProductsCartUseCase @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getCartUseCase: GetCartUseCase
) {
    suspend operator fun invoke(): BaseResultUseCase<List<ProductCartModel>> {
        return try {
            val responseUser = getUserUseCase()
            when(responseUser){
                is BaseResultUseCase.Error -> BaseResultUseCase.Error(responseUser.exception)
                BaseResultUseCase.NoInternetConnection -> BaseResultUseCase.NoInternetConnection
                BaseResultUseCase.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                is BaseResultUseCase.Success -> {
                    val responseCar = getCartUseCase(responseUser.data.id)
                    when(responseCar){
                        is BaseResultUseCase.Error -> BaseResultUseCase.Error(responseCar.exception)
                        BaseResultUseCase.NoInternetConnection -> BaseResultUseCase.NoInternetConnection
                        BaseResultUseCase.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                        is BaseResultUseCase.Success -> BaseResultUseCase.Success(responseCar.data.list)
                    }
                }
            }
        } catch (e: Exception) {
            BaseResultUseCase.Error(e)
        }
    }
}