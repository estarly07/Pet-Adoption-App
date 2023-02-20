package com.estarly.petadoptionapp.data.repositories

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.data.api.clients.ProductsClient
import com.estarly.petadoptionapp.data.api.response.ProductResponse
import com.estarly.petadoptionapp.data.api.response.TypeProductResponse
import com.estarly.petadoptionapp.ui.model.ProductModel
import com.estarly.petadoptionapp.ui.model.TypeProductModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepository @Inject constructor(
    private val productsClient: ProductsClient
){
    suspend fun getProducts() : BaseResultRepository<List<ProductModel>>{
        return try {
            val response = productsClient.getProducts()
            if(response == null || response.isEmpty()){
                BaseResultRepository.NullOrEmptyData
            }else{
                BaseResultRepository.Success(response.map { it.toData() })
            }
        }catch (e : Exception){
            BaseResultRepository.Error(e)
        }
    }
    suspend fun getTypesProducts() : BaseResultRepository<List<TypeProductModel>>{
        return try {
            val response = productsClient.getTypeProducts()
            if(response==null || response.isEmpty()){
                BaseResultRepository.NullOrEmptyData
            }else{
                BaseResultRepository.Success(response.map { it.toData() })
            }
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
fun TypeProductResponse.toData() : TypeProductModel = TypeProductModel(
    id   = this.id,
    name = this.name
)