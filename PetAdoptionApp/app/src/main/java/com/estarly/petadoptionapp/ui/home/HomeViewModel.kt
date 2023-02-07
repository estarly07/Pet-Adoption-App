package com.estarly.petadoptionapp.ui.home

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
    private val _showProgressPromotion = MutableLiveData<Boolean>()
    val showProgressPromotion : LiveData<Boolean> = _showProgressPromotion
    private  val _promotion = MutableLiveData<PromotionModel?>()
    val promotion : LiveData<PromotionModel?> = _promotion

    fun onCreate(){
        viewModelScope.launch{
            _showProgressPromotion.value = true
            val responsePromotion = getPromotionUseCase()
            _showProgressPromotion.value = false
            when(responsePromotion){
                is BaseResultUseCase.Error -> {}
                BaseResultUseCase.NoInternetConnection -> {}
                BaseResultUseCase.NullOrEmptyData -> {}
                is BaseResultUseCase.Success -> _promotion.value = responsePromotion.data
            }
        }
    }
}