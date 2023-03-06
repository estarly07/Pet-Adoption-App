package com.estarly.petadoptionapp.ui.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.products.GetProductsUseCase
import com.estarly.petadoptionapp.domain.products.GetTypesProductsUseCase
import com.estarly.petadoptionapp.domain.model.ProductModel
import com.estarly.petadoptionapp.domain.products.GetCantProductsCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCantProductsCartUseCase : GetCantProductsCartUseCase
) : ViewModel() {
    private val _cantProductsCart = MutableLiveData<Int>()
    val cantProductsCart: LiveData<Int> = _cantProductsCart
    private var _listProducts = MutableLiveData<List<ProductModel>>()
    val listProducts : LiveData<List<ProductModel>> = _listProducts
    private var _showProgressProducts = MutableLiveData<Boolean>()
    val showProgressProducts : LiveData<Boolean> = _showProgressProducts

    fun onCreate() {
        viewModelScope.launch {
            getCantProductsCart()
            _showProgressProducts.value = true
            val response = getProductsUseCase()
            _showProgressProducts.value = false
            when (response) {
                is BaseResultUseCase.Success -> {
                    _listProducts.value = response.data
                }
                is BaseResultUseCase.Error -> {}
                BaseResultUseCase.NoInternetConnection -> {}
                BaseResultUseCase.NullOrEmptyData -> {}
            }
        }
    }

    fun getCantProductsCart() {
        viewModelScope.launch {
            when (val response = getCantProductsCartUseCase()) {
                is BaseResultUseCase.Error -> _cantProductsCart.value = 0
                BaseResultUseCase.NoInternetConnection -> _cantProductsCart.value = 0
                BaseResultUseCase.NullOrEmptyData -> _cantProductsCart.value = 0
                is BaseResultUseCase.Success -> _cantProductsCart.value = response.data
            }
        }
    }
}
