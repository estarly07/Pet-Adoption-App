package com.estarly.petadoptionapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.login.LoginByEmailAndPassUseCase
import com.estarly.petadoptionapp.domain.login.RegisterUserUseCase
import com.estarly.petadoptionapp.domain.login.SetLoginPreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginByEmailAndPassUseCase: LoginByEmailAndPassUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val setLoginPreferencesUseCase: SetLoginPreferencesUseCase,
) : ViewModel(){
    //login screen
    private val _showProgressLogin = MutableLiveData<Boolean>()
    val showProgressLogin : LiveData<Boolean> = _showProgressLogin
    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email
    private val _pass = MutableLiveData<String>()
    val pass : LiveData<String> = _pass
    private val _errorEmail = MutableLiveData<String>()
    val errorEmail : LiveData<String> = _errorEmail
    private val _errorPass = MutableLiveData<String>()
    val errorPass : LiveData<String> = _errorPass

    // register screen
    private val _showProgressRegister = MutableLiveData<Boolean>()
    val showProgressRegister : LiveData<Boolean> = _showProgressRegister
    private val _emailRegister = MutableLiveData<String>()
    val emailRegister : LiveData<String> = _emailRegister
    private val _passRegister = MutableLiveData<String>()
    val passRegister : LiveData<String> = _passRegister
    private val _nameRegister = MutableLiveData<String>()
    val nameRegister : LiveData<String> = _nameRegister
    private val _errorNameRegister = MutableLiveData<String>()
    val errorNameRegister : LiveData<String> = _errorNameRegister
    private val _errorEmailRegister = MutableLiveData<String>()
    val errorEmailRegister : LiveData<String> = _errorEmailRegister
    private val _errorPassRegister = MutableLiveData<String>()
    val errorPassRegister : LiveData<String> = _errorPassRegister

    //other
    private val _goToHome = MutableLiveData<Boolean>()
    val goToHome : LiveData<Boolean> = _goToHome
    private val _showRegisterScreen = MutableLiveData<Boolean>()
    val showRegisterScreen : LiveData<Boolean> = _showRegisterScreen


    init {
        _email.value = ""
        _pass.value = ""
        _emailRegister.value =""
        _passRegister.value =""
        _nameRegister.value =""
        _showRegisterScreen.value = false
    }
    fun isLogin(){
        _goToHome.value = setLoginPreferencesUseCase.getIsLogin()
    }
    fun login(){
        viewModelScope.launch {
            if(email.value!!.trim().isEmpty()){
                _errorEmail.value = "This field is required"
                return@launch
            }
            if(pass.value!!.trim().isEmpty()){
                _errorPass.value = "This field is required"
                return@launch
            }
            _showProgressLogin.value = true
            val response = loginByEmailAndPassUseCase(email.value!!.trim(),pass.value!!.trim())
            when(response){
                is BaseResultUseCase.Error -> {
                    response.exception.message?.let { Log.i("TAG", it) }
                }
                is BaseResultUseCase.Success -> {
                    _goToHome.value = response.data
                }
                BaseResultUseCase.NoInternetConnection -> TODO()
                BaseResultUseCase.NullOrEmptyData -> TODO()
            }
            _showProgressLogin.value = false
        }

    }
    fun register(){
        viewModelScope.launch {
            if(emailRegister.value!!.trim().isEmpty()){
                _errorEmailRegister.value = "This field is required"
                return@launch
            }
            if(passRegister.value!!.trim().isEmpty()){
                _errorPassRegister.value = "This field is required"
                return@launch
            }
            if(nameRegister.value!!.trim().isEmpty()){
                _errorNameRegister.value = "This field is required"
                return@launch
            }
            _showProgressRegister.value = true
            when(val response = registerUserUseCase(nameRegister.value!!.trim(), emailRegister.value!!.trim(),passRegister.value!!.trim())){
                is BaseResultUseCase.Error -> {
                    response.exception.message?.let { Log.i("TAG", it) }
                }
                is BaseResultUseCase.Success -> {
                    _goToHome.value = response.data
                }
                BaseResultUseCase.NoInternetConnection -> TODO()
                BaseResultUseCase.NullOrEmptyData -> TODO()
            }
            _showProgressRegister.value = false
        }

    }
    fun changeTextEmail(email : String){
        _errorEmail.value = ""
        _email.value = email
    }
    fun changeTextPass(pass : String){
        _errorPass.value = ""
        _pass.value = pass
    }
    fun changeTextEmailRegister(email : String){
        _errorEmailRegister.value = ""
        _emailRegister.value = email
    }
    fun changeTextPassRegister(pass : String){
        _errorPassRegister.value = ""
        _passRegister.value = pass
    }
    fun changeTextNameRegister(name : String){
        _errorNameRegister.value = ""
        _nameRegister.value = name
    }
    fun showRegisterScreen() {_showRegisterScreen.value = true }
    fun showLoginScreen() { _showRegisterScreen.value = false}

    fun onBackPressed(loginActivity: LoginActivity) {
        if(_showRegisterScreen.value == true){
            _showRegisterScreen.value = false
        }else{
            loginActivity.finish()
        }
    }
}