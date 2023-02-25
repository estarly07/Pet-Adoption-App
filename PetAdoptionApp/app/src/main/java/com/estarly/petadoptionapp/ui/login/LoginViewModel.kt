package com.estarly.petadoptionapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel(){
    private val _showProgressLogin = MutableLiveData<Boolean>()
    val showProgressLogin : LiveData<Boolean> = _showProgressLogin
    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email
    private val _pass = MutableLiveData<String>()
    val pass : LiveData<String> = _pass

    fun login(){
        viewModelScope.launch {
            _showProgressLogin.value = true
            delay(5000)
            _showProgressLogin.value = false
        }

    }
    fun changeTextEmail(email : String){ _email.value = email }
    fun changeTextPass(pass : String){ _pass.value = pass }
}