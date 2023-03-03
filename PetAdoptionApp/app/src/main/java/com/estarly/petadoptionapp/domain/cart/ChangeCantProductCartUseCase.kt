package com.estarly.petadoptionapp.domain.cart

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.CartRepository
import com.estarly.petadoptionapp.domain.model.CartModel
import com.estarly.petadoptionapp.domain.model.ProductCartModel
import com.estarly.petadoptionapp.domain.user.GetUserUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChangeCantProductCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val getUserUseCase: GetUserUseCase
){
    suspend operator fun invoke(list: MutableList<ProductCartModel>) : BaseResultUseCase<Boolean> {
        return try {
            when(val responseUser = getUserUseCase()){
                is BaseResultUseCase.Error -> BaseResultUseCase.Error(responseUser.exception)
                BaseResultUseCase.NoInternetConnection ->  BaseResultUseCase.NoInternetConnection
                BaseResultUseCase.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                is BaseResultUseCase.Success -> {
                    when(val responseCart = cartRepository.increaseProductCart(
                        CartModel(
                            idCart = responseUser.data.id,
                            list =  list
                        )
                    )){
                        BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                        is BaseResultRepository.Success -> BaseResultUseCase.Success(responseCart.data)
                        is BaseResultRepository.Error -> BaseResultUseCase.Error(responseCart.exception)
                    }
                }
            }
        }catch (e : Exception){
            e.printStackTrace()
            BaseResultUseCase.Error(e)
        }
    }
}