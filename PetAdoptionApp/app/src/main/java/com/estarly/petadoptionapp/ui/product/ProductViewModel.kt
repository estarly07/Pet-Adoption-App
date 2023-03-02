package com.estarly.petadoptionapp.ui.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.cart.AddProductCartUseCase
import com.estarly.petadoptionapp.domain.model.ProductModel
import com.estarly.petadoptionapp.domain.user.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val addProductCartUseCase: AddProductCartUseCase,
    private val userUseCase: GetUserUseCase
): ViewModel(){

    private val _showMoreAbout = MutableLiveData<Boolean>()
    val showMoreAbout: LiveData<Boolean> = _showMoreAbout
    private val _imageSelect = MutableLiveData<String>()
    val imageSelect: LiveData<String> = _imageSelect
    private val _cant = MutableLiveData<Int>()
    val cant : LiveData<Int> = _cant

    init {
        _cant.value = 1
        _showMoreAbout.value = false
    }
    fun addCant(max: Int) {
        if(_cant.value!! < max)
            _cant.value = _cant.value!! + 1
    }
    fun subtractCant(){
        if(_cant.value!! > 1)
            _cant.value = _cant.value!! - 1
    }
    fun changeSelectImage(image: String){
        _imageSelect.value = image
    }
    fun showMoreAbout() { _showMoreAbout.value = !_showMoreAbout.value!! }

    fun addProductCart(productModel: ProductModel){
       viewModelScope.launch {
           when(val response = userUseCase()){
               is BaseResultUseCase.Error -> TODO()
               BaseResultUseCase.NoInternetConnection -> TODO()
               BaseResultUseCase.NullOrEmptyData -> TODO()
               is BaseResultUseCase.Success ->{
                   when(val responseAddCart = addProductCartUseCase(response.data.id,_cant.value!!,productModel)){
                       is BaseResultUseCase.Error -> Log.i("TAG","PUTO ERROR ${responseAddCart.exception.message}")
                       BaseResultUseCase.NoInternetConnection -> TODO()
                       BaseResultUseCase.NullOrEmptyData ->Log.i("TAG","PUTO NULL")
                       is BaseResultUseCase.Success -> {

                       }
                   }
               }
           }
       }
    }
}