package com.estarly.petadoptionapp.domain.products

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.ProductsRepository
import com.estarly.petadoptionapp.domain.model.ProductModel
import com.estarly.petadoptionapp.domain.model.TypeProductModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetProductsUseCaseTest {
    @RelaxedMockK
    private lateinit var typesProductsUseCase: GetTypesProductsUseCase

    @RelaxedMockK
    private lateinit var productsRepository: ProductsRepository

    private lateinit var getProductsUseCase: GetProductsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getProductsUseCase = GetProductsUseCase(
            typesProductsUseCase,
            productsRepository
        )
    }

    @Test
    fun `When the products are obtained, each product must have its respective name of the type of product`() = runBlocking {
        coEvery { typesProductsUseCase() } returns BaseResultUseCase.Success(
            listOf(
                TypeProductModel(0, "type one"),
                TypeProductModel(1, "type two"),
            )
        )
        coEvery { productsRepository.getProducts() } returns BaseResultRepository.Success(
            listOf(
                ProductModel(aboutProduct = "", idProduct = 0, cant = 2, idTypeProduct = 0, imageProduct = "", nameProduct = "", price = 15.5 , images = listOf()),
                ProductModel(aboutProduct = "", idProduct = 0, cant = 2, idTypeProduct = 1, imageProduct = "", nameProduct = "", price = 15.5 , images = listOf()),
                ProductModel(aboutProduct = "", idProduct = 0, cant = 2, idTypeProduct = 1, imageProduct = "", nameProduct = "", price = 15.5 , images = listOf()),
            )
        )

        val response = getProductsUseCase()

        assert(response is BaseResultUseCase.Success){
            "getProductsUseCase retorno un resultado que no es esperado => $response "
        }

        assert(
            (response as BaseResultUseCase.Success).data.count { it.nameTypeProduct == "type one" } == 1 &&
            response.data.count {  it.nameTypeProduct == "type two" } == 2
        ){
            "La respuesta de getProductsUseCase no seteo de buena forma el campo nameTypeProduct ya que se esperaba 1 del tipo TYPE_ONE y se obtuvo => ${response.data.count { it.nameTypeProduct == "type one" }}" +
                    " y 2 del tipo TYPE_TWO y se obtuvo => ${response.data.count { it.nameTypeProduct == "type two" }}"
        }
    }
}