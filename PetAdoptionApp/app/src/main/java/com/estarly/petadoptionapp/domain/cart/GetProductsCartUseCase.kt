package com.estarly.petadoptionapp.domain.cart

import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.model.ProductCartModel
import com.estarly.petadoptionapp.domain.model.ProductModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetProductsCartUseCase @Inject constructor() {
    suspend operator fun invoke(): BaseResultUseCase<List<ProductCartModel>> {
        return try {
            BaseResultUseCase.Success(
                listOf(
                    ProductCartModel(
                        productModel = ProductModel(
                            idProduct = 1,
                            nameProduct = "Bakers",
                            aboutProduct = "sdfsdfsdfsdf",
                            images = listOf(),
                            imageProduct = "https://www.purina.co.uk/sites/default/files/2022-12/NUK1915%20Bakers-MHI-Pack-3kg%20adult%20beef_hero.png",
                            cant = 24,
                            price = 12.34,
                            idTypeProduct = 0,
                            nameTypeProduct = ""
                        ),
                        cant = 15
                    ),
                    ProductCartModel(
                        productModel = ProductModel(
                            idProduct = 1,
                            nameProduct = "Bakers",
                            aboutProduct = "sdfsdfsdfsdf",
                            images = listOf(),
                            imageProduct = "https://www.purina.co.uk/sites/default/files/2022-12/NUK1915%20Bakers-MHI-Pack-3kg%20adult%20beef_hero.png",
                            cant = 4,
                            price = 105.5,
                            idTypeProduct = 0,
                            nameTypeProduct = ""
                        ),
                        cant = 15
                    ),
                )
            )
        } catch (e: Exception) {
            BaseResultUseCase.Error(e)
        }
    }
}