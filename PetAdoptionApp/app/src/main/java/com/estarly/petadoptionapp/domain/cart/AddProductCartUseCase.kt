package com.estarly.petadoptionapp.domain.cart

import android.util.Log
import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.CartRepository
import com.estarly.petadoptionapp.domain.model.CartModel
import com.estarly.petadoptionapp.domain.model.ProductCartModel
import com.estarly.petadoptionapp.domain.model.ProductModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddProductCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val getCartUseCase: GetCartUseCase
){
    val tag ="AddProductCartUseCase"
    suspend operator fun invoke(uid:String,cant : Int, productModel: ProductModel) : BaseResultUseCase<Boolean>{
        return try {
            when(val responseCart = getCartUseCase(uid)){
                BaseResultUseCase.NoInternetConnection -> BaseResultUseCase.NoInternetConnection
                BaseResultUseCase.NullOrEmptyData ->{
                    val cart = CartModel(
                        uid,
                        mutableListOf(
                            ProductCartModel(
                                productModel,
                                cant
                            )
                        )
                    )
                    callUpdateCart(uid,cart)
                }
                is BaseResultUseCase.Success -> {
                    val cart = responseCart.data
                    cart.list.add(ProductCartModel(
                        productModel,
                        cant
                    ))
                    callUpdateCart(uid,cart)
                }
                is BaseResultUseCase.Error -> {
                    Log.i(tag,"${responseCart.exception.message}")
                    BaseResultUseCase.Error(responseCart.exception)
                }
            }
        }catch (e : Exception){
            e.printStackTrace()
            BaseResultUseCase.Error(e)
        }
    }
    private suspend fun callUpdateCart(uid: String, cart: CartModel) : BaseResultUseCase<Boolean>{
        return when(val responseUpdateCart = cartRepository.updateCart(uid,cart)){
            is BaseResultRepository.Error -> {
                Log.i(tag,"callUpdateCart => ${responseUpdateCart.exception.message}")
                BaseResultUseCase.Error(responseUpdateCart.exception)
            }
            BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
            is BaseResultRepository.Success -> BaseResultUseCase.Success(true)
        }
    }
}