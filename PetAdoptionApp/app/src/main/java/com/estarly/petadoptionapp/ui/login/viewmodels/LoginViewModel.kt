package com.estarly.petadoptionapp.ui.login.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.login.LoginByEmailAndPassUseCase
import com.estarly.petadoptionapp.domain.login.LoginByGoogleUseCase
import com.estarly.petadoptionapp.domain.login.RegisterUserUseCase
import com.estarly.petadoptionapp.domain.login.SetLoginPreferencesUseCase
import com.estarly.petadoptionapp.ui.login.LoginActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginByEmailAndPassUseCase: LoginByEmailAndPassUseCase,
    private val loginByGoogleUseCase      : LoginByGoogleUseCase,
    private val registerUserUseCase       : RegisterUserUseCase,
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
    private val splashShowFlow = MutableStateFlow(true)
    val isSplashShow = splashShowFlow.asStateFlow()
    private val _showDialogGoogle = MutableLiveData<Boolean>()
    val showDialogGoogle : LiveData<Boolean> = _showDialogGoogle
    init {
        _email.value              = ""
        _pass.value               = ""
        _emailRegister.value      = ""
        _passRegister.value       = ""
        _nameRegister.value       = ""
        _showRegisterScreen.value = false
    }
    /**
     *
     * Esta función verifica el estado de inicio de sesión del usuario.
     *
     * Esta función llama al caso de uso `setLoginPreferencesUseCase.getIsLogin()` para determinar
     * si el usuario ya ha iniciado sesión. El resultado de esta verificación se asigna a la propiedad
     * `_goToHome.value`, lo que puede desencadenar la navegación hacia la pantalla de inicio si el
     * usuario está autenticado.
     */
    fun isLogin(){
        _goToHome.value = setLoginPreferencesUseCase.getIsLogin()
        splashShowFlow.value = false
    }
    /**
     * Esta función inicia el proceso de login del usuario.
     *
     * Esta función se encarga de manejar el flujo completo del proceso de login del usuario.
     * Incluye la validación de las credenciales del usuario, la comunicación con el servidor de autenticación,
     * el manejo de respuestas y errores, y la actualización del estado de autenticación en la aplicación.
     */
    fun login(context:Context){
        viewModelScope.launch {
            if(email.value!!.trim().isEmpty()){
                _errorEmail.value = context.getString(R.string.this_field_is_required)
                return@launch
            }
            if(pass.value!!.trim().isEmpty()){
                _errorPass.value = context.getString(R.string.this_field_is_required)
                return@launch
            }
            _showProgressLogin.value = true
            when(val response = loginByEmailAndPassUseCase(email.value!!.trim(),pass.value!!.trim())){
                is BaseResultUseCase.Error             -> response.exception.message?.let { Log.i("TAG", it) }
                is BaseResultUseCase.Success           -> _goToHome.value = response.data
                BaseResultUseCase.NoInternetConnection -> TODO()
                BaseResultUseCase.NullOrEmptyData      -> TODO()
            }
            _showProgressLogin.value = false
        }
    }
    /**
     * Esta función inicia el proceso de registro de un nuevo usuario.
     *
     * Esta función es responsable de iniciar el flujo necesario para registrar un nuevo usuario en el sistema.
     * Puede incluir la recopilación de información del usuario, la validación de datos, y la comunicación
     * con el servidor para crear una nueva cuenta de usuario.
     */
    fun register(context:Context){
        viewModelScope.launch {
            if(emailRegister.value!!.trim().isEmpty()){
                _errorEmailRegister.value = context.getString(R.string.this_field_is_required)
                return@launch
            }
            if(passRegister.value!!.trim().isEmpty()){
                _errorPassRegister.value = context.getString(R.string.this_field_is_required)
                return@launch
            }
            if(nameRegister.value!!.trim().isEmpty()){
                _errorNameRegister.value = context.getString(R.string.this_field_is_required)
                return@launch
            }
            _showProgressRegister.value = true
            when(val response = registerUserUseCase(nameRegister.value!!.trim(), emailRegister.value!!.trim(),passRegister.value!!.trim())){
                is BaseResultUseCase.Error             -> response.exception.message?.let { Log.i("TAG", it) }
                is BaseResultUseCase.Success           -> _goToHome.value = response.data
                BaseResultUseCase.NoInternetConnection -> TODO()
                BaseResultUseCase.NullOrEmptyData      -> TODO()
            }
            _showProgressRegister.value = false
        }

    }
    fun changeTextEmail(email : String){
        _errorEmail.value = ""
        _email.value      = email
    }
    fun changeTextPass(pass : String){
        _errorPass.value = ""
        _pass.value      = pass
    }
    fun changeTextEmailRegister(email : String){
        _errorEmailRegister.value = ""
        _emailRegister.value      = email
    }
    fun changeTextPassRegister(pass : String){
        _errorPassRegister.value = ""
        _passRegister.value      = pass
    }
    fun changeTextNameRegister(name : String){
        _errorNameRegister.value = ""
        _nameRegister.value      = name
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
    /**
     *
     * Esta función activa la visualización del diálogo de Google.
     *
     * La función `showDialogGoogle` establece el valor de `_showDialogGoogle` a `true`, lo que
     * desencadena la visualización del diálogo de Google en la interfaz de usuario.
     */
    fun showDialogGoogle() { _showDialogGoogle.value = true }
    /**
     *
     * Cierra el diálogo de Google.
     *
     * Esta función se encarga de cerrar el diálogo de Google estableciendo el valor de `_showDialogGoogle`
     * a `false`. Al llamar a esta función, se actualiza el estado interno que controla la visibilidad del
     * diálogo, asegurando que el diálogo se cierre correctamente en la interfaz de usuario.
     */
    fun closeDialogGoogle(){ _showDialogGoogle.value = false }
    /**
     *
     * @param idToken El token de identificación proporcionado por Google.
     *
     * Inicia sesión con Google utilizando el token de identificación.
     *
     * Esta función inicia el proceso de inicio de sesión utilizando las credenciales de Google.
     * Recibe un token de identificación (`idToken`) proporcionado por los servicios de autenticación de Google.
     * La función se encarga de validar este token y establecer una sesión autenticada para el usuario en la aplicación.
     */
    fun loginByGoogle(idToken: String) {
        viewModelScope.launch {
            _showProgressLogin.value = true
            when(val response = loginByGoogleUseCase(idToken = idToken)){
                is BaseResultUseCase.Error             -> response.exception.message?.let { Log.i("TAG", it) }
                is BaseResultUseCase.Success           -> _goToHome.value = response.data
                BaseResultUseCase.NoInternetConnection -> TODO()
                BaseResultUseCase.NullOrEmptyData      -> TODO()
            }
            _showProgressLogin.value = false
        }
    }
}