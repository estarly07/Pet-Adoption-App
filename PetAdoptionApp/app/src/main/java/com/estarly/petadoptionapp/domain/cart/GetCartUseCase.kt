package com.estarly.petadoptionapp.domain.cart

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.CartRepository
import com.estarly.petadoptionapp.domain.model.CartModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
){
    suspend operator fun invoke (uid : String) : BaseResultUseCase<CartModel>{
        return try {
            when(val response = cartRepository.getCart(uid)){
                is BaseResultRepository.Error -> BaseResultUseCase.Error(response.exception)
                BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                is BaseResultRepository.Success -> BaseResultUseCase.Success(response.data)
            }
        }catch (e : Exception){
            e.printStackTrace()
            BaseResultUseCase.Error(e)
        }
    }

}
