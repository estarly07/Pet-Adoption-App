package com.estarly.petadoptionapp.ui.breed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.pets.GetPetsUseCase
import com.estarly.petadoptionapp.ui.model.PetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedViewModel @Inject constructor(private val getPetsUseCase : GetPetsUseCase) : ViewModel() {
    private val _pets = MutableLiveData<List<PetModel>>()
    val pets : LiveData<List<PetModel>> = _pets

    fun getPets(idBreed : Int){
        _pets.value = listOf()
        viewModelScope.launch {
            when(val response = getPetsUseCase(idBreed)){
                is BaseResultUseCase.Error -> {}
                BaseResultUseCase.NoInternetConnection -> {}
                BaseResultUseCase.NullOrEmptyData -> {}
                is BaseResultUseCase.Success -> {_pets.value = response.data}
            }
        }
    }
}
