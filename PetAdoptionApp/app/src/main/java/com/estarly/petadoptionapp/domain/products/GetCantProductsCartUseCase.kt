package com.estarly.petadoptionapp.domain.products

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.CartRepository
import com.estarly.petadoptionapp.data.repositories.ProductsRepository
import com.estarly.petadoptionapp.domain.user.GetUserUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCantProductsCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val getUserUseCase: GetUserUseCase
) {
    suspend operator fun invoke() : BaseResultUseCase<Int>{
        return try {
            val responseUser = getUserUseCase();
            when(responseUser){
                is BaseResultUseCase.Success -> {
                    val response = cartRepository.getCart(responseUser.data.id)
                    when(response){
                        is BaseResultRepository.Success -> {
                            BaseResultUseCase.Success(response.data.list.size)
                        }
                        is BaseResultRepository.Error -> BaseResultUseCase.Error(response.exception)
                        BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                    }
                }
                is BaseResultUseCase.Error -> BaseResultUseCase.Error(responseUser.exception)
                BaseResultUseCase.NoInternetConnection -> BaseResultUseCase.NoInternetConnection
                BaseResultUseCase.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
            }
        }catch (e :Exception){
            BaseResultUseCase.Error(e)
        }
    }
}