package com.estarly.petadoptionapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.login.LoginByEmailAndPassUseCase
import com.estarly.petadoptionapp.domain.login.SetLoginPreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginByEmailAndPassUseCase: LoginByEmailAndPassUseCase,
    private val setLoginPreferencesUseCase: SetLoginPreferencesUseCase
) : ViewModel(){
    private val _showProgressLogin = MutableLiveData<Boolean>()
    val showProgressLogin : LiveData<Boolean> = _showProgressLogin
    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email
    private val _pass = MutableLiveData<String>()
    val pass : LiveData<String> = _pass
    private val _goToHome = MutableLiveData<Boolean>()
    val goToHome : LiveData<Boolean> = _goToHome
    private val _errorEmptyEmail = MutableLiveData<String>()
    val errorEmptyEmail : LiveData<String> = _errorEmptyEmail
    private val _errorEmptyPass = MutableLiveData<String>()
    val errorEmptyPass : LiveData<String> = _errorEmptyPass

    init {
        _email.value = ""
        _pass.value = ""
    }
    fun isLogin(){
        _goToHome.value = setLoginPreferencesUseCase.getIsLogin()
    }
    fun login(){
        viewModelScope.launch {
            if(email.value!!.isEmpty()){
                _errorEmptyEmail.value = "This field is required"
                return@launch
            }
            if(pass.value!!.isEmpty()){
                _errorEmptyPass.value = "This field is required"
                return@launch
            }
            _showProgressLogin.value = true
            val response = loginByEmailAndPassUseCase(email.value!!,pass.value!!)
            when(response){
                is BaseResultUseCase.Error -> {
                    response.exception.message?.let { Log.i("TAG", it) }
                    _showProgressLogin.value = false
                }
                is BaseResultUseCase.Success -> {
                    _goToHome.value = response.data
                    _showProgressLogin.value = false
                }
                BaseResultUseCase.NoInternetConnection -> TODO()
                BaseResultUseCase.NullOrEmptyData -> TODO()
            }
        }

    }
    fun changeTextEmail(email : String){
        _errorEmptyEmail.value = ""
        _email.value = email
    }
    fun changeTextPass(pass : String){
        _errorEmptyPass.value = ""
        _pass.value = pass
    }
}