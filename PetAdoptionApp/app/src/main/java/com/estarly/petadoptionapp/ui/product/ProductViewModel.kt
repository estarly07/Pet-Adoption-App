package com.estarly.petadoptionapp.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(): ViewModel(){

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
}