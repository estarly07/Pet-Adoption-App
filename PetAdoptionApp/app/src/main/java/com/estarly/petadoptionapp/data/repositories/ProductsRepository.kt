package com.estarly.petadoptionapp.data.repositories

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.data.api.response.ProductResponse
import com.estarly.petadoptionapp.ui.model.ProductModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepository @Inject constructor(){
    suspend fun getProducts() : BaseResultRepository<List<ProductModel>>{
        return try {
            val list = listOf(
                ProductResponse(aboutProduct = "asdasd", idProduct = 0, cant = 2, idTypeProduct = 0, imageProduct = "https://blackhawkpetcare.com/media/2623/bh498-original-puppy-food-for-large-breeds-chicken-and-rice-600x961.png", nameProduct = "asd", price = 15.5 ,),
                ProductResponse(aboutProduct = "asdasd", idProduct = 0, cant = 2, idTypeProduct = 1, imageProduct = "https://blackhawkpetcare.com/media/2623/bh498-original-puppy-food-for-large-breeds-chicken-and-rice-600x961.png", nameProduct = "asd", price = 15.5 ,),
            )
            BaseResultRepository.Success(list.map { it.toData() })
        }catch (e : Exception){
            BaseResultRepository.Error(e)
        }
    }
    suspend fun getTypesProducts() : BaseResultRepository<List<Any>>{
        return try {
            BaseResultRepository.Success(listOf())
        }catch (e : Exception){
            BaseResultRepository.Error(e)
        }
    }
}
fun ProductResponse.toData() : ProductModel = ProductModel(
    idTypeProduct = this.idTypeProduct,
    idProduct     = this.idProduct,
    nameProduct   = this.nameProduct,
    price         = this.price,
    cant          = this.cant,
    imageProduct  = this.imageProduct,
    aboutProduct  = this.imageProduct
)