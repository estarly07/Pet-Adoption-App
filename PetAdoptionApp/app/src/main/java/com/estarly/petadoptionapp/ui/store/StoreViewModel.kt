package com.estarly.petadoptionapp.ui.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.products.GetProductsUseCase
import com.estarly.petadoptionapp.domain.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {
    private var _listProducts = MutableLiveData<List<ProductModel>>()
    val listProducts : LiveData<List<ProductModel>> = _listProducts
    private var _showProgressProducts = MutableLiveData<Boolean>()
    val showProgressProducts : LiveData<Boolean> = _showProgressProducts

    fun onCreate() {
        viewModelScope.launch {
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
}
