package com.estarly.petadoptionapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.promotion.GetPromotionUseCase
import com.estarly.petadoptionapp.ui.model.PromotionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val getPromotionUseCase: GetPromotionUseCase
): ViewModel() {
    private val TAG ="HomeViewModel"
    private val _showProgressPromotion = MutableLiveData<Boolean>()
    val showProgressPromotion : LiveData<Boolean> = _showProgressPromotion
    private  val _promotion = MutableLiveData<PromotionModel?>()
    val promotion : LiveData<PromotionModel?> = _promotion

    fun onCreate(){
        viewModelScope.launch{
            _showProgressPromotion.value = true// show progress promotion
            val responsePromotion = getPromotionUseCase()// get promotion
            Log.i(TAG,responsePromotion.toString())
            _showProgressPromotion.value = false// gone progress promotion
            when(responsePromotion){
                is BaseResultUseCase.Error -> {}
                BaseResultUseCase.NoInternetConnection -> {}
                BaseResultUseCase.NullOrEmptyData -> {}
                is BaseResultUseCase.Success -> _promotion.value = responsePromotion.data
            }
        }
    }
}