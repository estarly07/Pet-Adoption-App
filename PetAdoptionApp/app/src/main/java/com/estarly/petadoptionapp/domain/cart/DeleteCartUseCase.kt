package com.estarly.petadoptionapp.domain.cart

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.CartRepository
import com.estarly.petadoptionapp.domain.user.GetUserUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val getUserUseCase: GetUserUseCase
){
    suspend operator fun invoke() : BaseResultUseCase<Boolean>{
        return try {
            when(val responseUser = getUserUseCase()){
                is BaseResultUseCase.Error -> BaseResultUseCase.Error(responseUser.exception)
                BaseResultUseCase.NoInternetConnection -> BaseResultUseCase.NoInternetConnection
                BaseResultUseCase.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                is BaseResultUseCase.Success -> {
                    when(val response = cartRepository.deleteCart(responseUser.data.id)){
                        is BaseResultRepository.Error -> BaseResultUseCase.Error(response.exception)
                        BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                        is BaseResultRepository.Success -> BaseResultUseCase.Success(true)
                    }
                }
            }
        }catch (e : Exception){
            BaseResultUseCase.Error(e)
        }
    }
}