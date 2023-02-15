package com.estarly.petadoptionapp.ui.pet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(): ViewModel() {
    private var _showMoreAbout = MutableLiveData<Boolean>()
    val showMoreAbout : LiveData<Boolean> = _showMoreAbout
    init {
        _showMoreAbout.value = false
    }

    fun showMoreAbout() { _showMoreAbout.value = !_showMoreAbout.value!!
    }
}