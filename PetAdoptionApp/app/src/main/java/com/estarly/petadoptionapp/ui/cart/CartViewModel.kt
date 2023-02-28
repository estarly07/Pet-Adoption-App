package com.estarly.petadoptionapp.ui.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.cart.GetProductsCartUseCase
import com.estarly.petadoptionapp.domain.model.ProductCartModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getProductsCartUseCase: GetProductsCartUseCase
) : ViewModel() {
    private val _showAlertDialog = MutableLiveData<Boolean>()
    val showAlertDialog: LiveData<Boolean> = _showAlertDialog
    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice : LiveData<Double> = _totalPrice
    private val _listProducts = MutableLiveData<List<ProductCartModel>>()
    val listProducts : LiveData<List<ProductCartModel>> = _listProducts

    fun getProducts(){
        viewModelScope.launch {
            val response = getProductsCartUseCase()
            when(response){
                is BaseResultUseCase.Success -> {
                    _listProducts.value = response.data
                    calculatePrice()
                }
                is BaseResultUseCase.Error -> TODO()
                BaseResultUseCase.NoInternetConnection -> TODO()
                BaseResultUseCase.NullOrEmptyData -> TODO()
            }
        }
    }



    fun addSubtract(index: Int, product: ProductCartModel) {
        _listProducts.value?.let {
            if(it.get(index).cant >0){
                it.get(index).cant -= 1
                calculatePrice()
            }
        }
    }

    private fun calculatePrice() {
        Log.i("","${_totalPrice.value}")
        _listProducts.value?.let {
            var totalPrice = 0.0
            for (total in it){
                totalPrice += (total.productModel.price * total.cant)
            }
            _totalPrice.value = totalPrice
        }
    }

    fun addCant(index: Int, product: ProductCartModel) {
        _listProducts.value?.let {
            it.get(index).productModel.apply {
                it.get(index).cant += 1
            }
            calculatePrice()
        }
    }
    fun showDeleteCartAlertDialog(show : Boolean){ _showAlertDialog.value = show }
    fun deleteCart(){
        showDeleteCartAlertDialog(false)
        _listProducts.value= listOf()
        calculatePrice()
    }
}