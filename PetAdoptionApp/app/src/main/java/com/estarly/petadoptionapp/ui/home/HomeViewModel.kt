package com.estarly.petadoptionapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.GetBreedsUseCase
import com.estarly.petadoptionapp.domain.promotion.GetPromotionUseCase
import com.estarly.petadoptionapp.ui.model.BreedModel
import com.estarly.petadoptionapp.ui.model.PromotionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val getPromotionUseCase: GetPromotionUseCase,
  private val getBreedsUseCase   : GetBreedsUseCase
): ViewModel() {
    private val TAG ="HomeViewModel"

    private val _breeds = MutableLiveData<List<BreedModel>>()
    val breeds : LiveData<List<BreedModel>> = _breeds
    private val _showProgressPromotion = MutableLiveData<Boolean>()
    val showProgressPromotion : LiveData<Boolean> = _showProgressPromotion
    private  val _promotion = MutableLiveData<PromotionModel?>()
    val promotion : LiveData<PromotionModel?> = _promotion

    fun onCreate(){
        viewModelScope.launch{
            getPromotion()
            getBreeds()
        }
    }

    private suspend fun getBreeds() {
        //TODO Show progress
        val response = getBreedsUseCase()
        //TODO gone progress
        when(response){
            is BaseResultUseCase.Error -> TODO()
            BaseResultUseCase.NoInternetConnection -> TODO()
            BaseResultUseCase.NullOrEmptyData -> TODO()
            is BaseResultUseCase.Success -> _breeds.value = response.data
        }
    }

    private suspend fun getPromotion() {
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