package com.estarly.petadoptionapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.estarly.petadoptionapp.ui.MainActivity
import com.estarly.petadoptionapp.ui.ActivityStructure
import com.estarly.petadoptionapp.ui.login.screens.LoginScreen
import com.estarly.petadoptionapp.ui.login.screens.RegisterScreen
import com.estarly.petadoptionapp.ui.login.viewmodels.LoginViewModel
import com.estarly.petadoptionapp.ui.theme.PetAdoptionAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity(), ActivityStructure {
    private val loginViewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initObservers()
        getData()
    }
    /**
     * Inicializa las vistas de la pantalla.
     *
     * Esta función se utiliza para configurar e inicializar todos los elementos visuales (views) de la pantalla.
     * Se llama generalmente en el proceso de creación de la pantalla para asegurarse de que todas las vistas
     * están correctamente configuradas antes de interactuar con ellas.
     */
    override fun initView() {
        setContent {
            val showRegisterScreen by loginViewModel.showRegisterScreen.observeAsState(initial = false)
            PetAdoptionAppTheme(darkTheme = false) {
                if(showRegisterScreen) RegisterScreen(context = this,loginViewModel)
                else LoginScreen(context = this,loginViewModel)
            }
        }
    }
    /**
     *
     * Inicializa los observadores de la pantalla.
     *
     * Esta función es opcional y se utiliza para configurar los observadores necesarios, como los LiveData del
     * ViewModel.
     */
    override fun getData() {
        with(loginViewModel){
            isLogin()
        }
    }
    /**
     *
     * Esta función recupera la información necesaria para mostrar en la Activity.
     *
     * Esta función es utilizada para obtener los datos que serán presentados en la Activity.
     * La función encapsula la lógica de recuperación de datos desde las fuentes correspondientes,
     * que pueden incluir bases de datos locales, servicios web, entre otro
     *
     */
    override fun initObservers() {
        with(loginViewModel){
            goToHome.observe(this@LoginActivity){
                it?.let {
                    if(it){
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        finish()
                    }

                }
            }
        }
    }
    override fun onBackPressed() { loginViewModel.onBackPressed(this) }
}