package com.estarly.petadoptionapp.data.repositories

import android.util.Log
import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.data.api.firebase.Firebase
import com.estarly.petadoptionapp.data.api.response.CartResponse
import com.estarly.petadoptionapp.data.api.response.ProductCartResponse
import com.estarly.petadoptionapp.data.api.response.ProductResponse
import com.estarly.petadoptionapp.domain.model.CartModel
import com.estarly.petadoptionapp.domain.model.ProductCartModel
import com.estarly.petadoptionapp.domain.model.ProductModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(
    private val firebase: Firebase
){
    suspend  fun updateCart(uid: String, cart: CartModel) : BaseResultRepository<Boolean>{
        return try {
            val response = firebase.updateCart(uid,cart.toData().toJson())
            BaseResultRepository.Success(response)
        }catch (e : Exception){
            BaseResultRepository.Error(e)
        }
    }
    suspend  fun getCart(uid: String) : BaseResultRepository<CartModel>{
        return try {
            val response = firebase.getCart(uid)
            if (response.data == null){
                BaseResultRepository.NullOrEmptyData
            }else{
                Log.i("getCart",response.data!!.toString())
                BaseResultRepository.Success(CartResponse.fromJson(response.data!!).toData())
            }
        }catch (e : Exception){
            Log.i("getCart","${e.message}")
            BaseResultRepository.Error(e)
        }
    }

    suspend fun deleteCart(uid: String): BaseResultRepository<Boolean> {
        return try {
            val response = firebase.deleteCart(uid)
            BaseResultRepository.Success(response)
        }catch (e : Exception){
            BaseResultRepository.Error(e)
        }
    }

    suspend fun increaseProductCart(cart: CartModel): BaseResultRepository<Boolean> {
        return try {
            val response = firebase.increaseProductCart(cart.idCart,cart.toData().toJson())
            BaseResultRepository.Success(response)
        }catch (e : Exception){
            BaseResultRepository.Error(e)
        }
    }
}

private fun CartModel.toData(): CartResponse =
    CartResponse(
        idCart,
        list.map { it.toData() }.toList() as MutableList<ProductCartResponse>
    )


private fun CartResponse.toData(): CartModel =
    CartModel(
        idCart,
        list.map { it.toData() }.toMutableList()
    )
private fun ProductCartResponse.toData(): ProductCartModel =
    ProductCartModel(
        productResponse.toData(),
        cant
    )
private fun ProductCartModel.toData(): ProductCartResponse =
    ProductCartResponse(
        productModel.toData(),
        cant
    )
fun ProductModel.toData() : ProductResponse = ProductResponse(
    idTypeProduct = this.idTypeProduct,
    idProduct     = this.idProduct,
    nameProduct   = this.nameProduct,
    price         = this.price,
    cant          = this.cant,
    imageProduct  = this.imageProduct,
    aboutProduct  = this.aboutProduct,
    images        = this.images
)
