package com.estarly.petadoptionapp.domain.cart

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.CartRepository
import com.estarly.petadoptionapp.domain.model.CartModel
import com.estarly.petadoptionapp.domain.model.ProductModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class AddProductCartUseCaseTest{
    @RelaxedMockK private lateinit var cartRepository :CartRepository
    @RelaxedMockK private lateinit var getCartUseCase: GetCartUseCase
    private lateinit var addProductCartUseCase: AddProductCartUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        addProductCartUseCase = AddProductCartUseCase(cartRepository = cartRepository, getCartUseCase = getCartUseCase)
    }
    @Test
    fun `When getCartUseCase returns NullOrEmptyData, a cart should be created with the selected product and return success`() = runBlocking{
        val cartModel = CartModel(idCart = "", list = mutableListOf())
        val productModel = ProductModel(
            nameProduct = "", nameTypeProduct = "", imageProduct = "", idTypeProduct = 1, aboutProduct = "", idProduct = 1, images = listOf(), price = 0.0, cant = 0
        )
        coEvery { getCartUseCase("") } returns BaseResultUseCase.NullOrEmptyData
        coEvery { cartRepository.updateCart("",cartModel) } returns  BaseResultRepository.Success(true)
        val response = addProductCartUseCase("",1,productModel)

        coVerify(exactly = 1) {cartRepository.updateCart("",cartModel)}
        assert(response is BaseResultUseCase.Success){"The response was expected to be sucess."}
        assert((response as BaseResultUseCase.Success).data){"The data was expected to be true"}
    }
}