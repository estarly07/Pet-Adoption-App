package com.estarly.petadoptionapp.ui.breed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estarly.petadoptionapp.ui.model.PetModel

class BreedViewModel : ViewModel() {
    private val _pets = MutableLiveData<List<PetModel>>()
    val pets : LiveData<List<PetModel>> = _pets
}