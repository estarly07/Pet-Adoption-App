package com.estarly.petadoptionapp.ui.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.cart.DeleteCartUseCase
import com.estarly.petadoptionapp.domain.cart.GetProductsCartUseCase
import com.estarly.petadoptionapp.domain.cart.ChangeCantProductCartUseCase
import com.estarly.petadoptionapp.domain.model.ProductCartModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getProductsCartUseCase: GetProductsCartUseCase,
    private val deleteCartUseCase: DeleteCartUseCase,
    private val changeCantProductCartUseCase: ChangeCantProductCartUseCase
) : ViewModel() {
    private val _showAlertDialog = MutableLiveData<Boolean>()
    val showAlertDialog: LiveData<Boolean> = _showAlertDialog
    private val _showDeleteProductAlertDialog = MutableLiveData<Int>()
    val showDeleteProductAlertDialog: LiveData<Int> = _showDeleteProductAlertDialog
    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice : LiveData<Double> = _totalPrice
    private val _listProducts = MutableLiveData<List<ProductCartModel>>()
    val listProducts : LiveData<List<ProductCartModel>> = _listProducts
    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress : LiveData<Boolean> = _showProgress

    fun getProducts(){
        viewModelScope.launch {
            _showProgress.value = true
            when(val response = getProductsCartUseCase()){
                is BaseResultUseCase.Success -> {
                    _listProducts.value = response.data
                    calculatePrice()
                }
                is BaseResultUseCase.Error -> {}
                BaseResultUseCase.NoInternetConnection -> {}
                BaseResultUseCase.NullOrEmptyData -> {}
            }
            _showProgress.value = false
        }
    }



    fun addSubtract(index: Int, product: ProductCartModel) {
        viewModelScope.launch {
            _listProducts.value?.let {
                if(it[index].cant >0){
                    if(it[index].cant == 1){
                        _showDeleteProductAlertDialog.value = index
                        return@launch
                    }
                    it[index].cant -= 1
                    calculatePrice()
                    changeCantProductCartUseCase(list = _listProducts.value as MutableList<ProductCartModel>)
                }
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
        viewModelScope.launch {
            _listProducts.value?.let {
                it[index].productModel.apply {
                    it[index].cant += 1
                }
                calculatePrice()
                changeCantProductCartUseCase(list = _listProducts.value as MutableList<ProductCartModel>)
            }
        }
    }
    fun showDeleteCartAlertDialog(show : Boolean){ _showAlertDialog.value = show }
    fun deleteCart(){
        viewModelScope.launch {
            showDeleteCartAlertDialog(false)
            when(val response = deleteCartUseCase()){
                is BaseResultUseCase.Error -> TODO()
                BaseResultUseCase.NoInternetConnection -> TODO()
                BaseResultUseCase.NullOrEmptyData -> TODO()
                is BaseResultUseCase.Success -> {
                    if(response.data){
                        _listProducts.value= listOf()
                        calculatePrice()
                    }
                }
            }
        }
    }

    fun goneDeleteProductAlertDialog() { _showDeleteProductAlertDialog.value = -1 }

    fun deleteProduct(index: Int) {
        viewModelScope.launch {
            val list =  _listProducts.value as MutableList<ProductCartModel>
            list.removeAt(index)
            _listProducts.value = list
            changeCantProductCartUseCase(list = _listProducts.value as MutableList<ProductCartModel>)
            _showDeleteProductAlertDialog.value = -1
            calculatePrice()
        }
    }
}