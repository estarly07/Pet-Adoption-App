package com.estarly.petadoptionapp.ui.breed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.pets.GetPetsUseCase
import com.estarly.petadoptionapp.ui.model.PetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedViewModel @Inject constructor(private val getPetsUseCase : GetPetsUseCase) : ViewModel() {
    private val _pets = MutableLiveData<List<PetModel>>()
    val pets : LiveData<List<PetModel>> = _pets
    private var _showProgressPets = MutableLiveData<Boolean>()
    val showProgressPets : LiveData<Boolean> = _showProgressPets

    fun getPets(idBreed : Int){
        viewModelScope.launch {
            _showProgressPets.value = true// show progress pet
            val response = getPetsUseCase(idBreed)//Get all pets by idBreed
            _showProgressPets.value = false// gone progress pet
            when(response){
                is BaseResultUseCase.Error -> {}
                BaseResultUseCase.NoInternetConnection -> {}
                BaseResultUseCase.NullOrEmptyData -> {}
                is BaseResultUseCase.Success -> {_pets.value = response.data}
            }
        }
    }

    fun clearData() {
        Log.i("CLEAR","CLEAR")
        _pets.value = listOf()
        Log.i("CLEAR","CLEAR ${_pets.value!!.size }")
    }
}
