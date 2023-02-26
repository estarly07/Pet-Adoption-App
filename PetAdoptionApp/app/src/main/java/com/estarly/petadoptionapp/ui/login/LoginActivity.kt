package com.estarly.petadoptionapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.estarly.petadoptionapp.ui.MainActivity
import com.estarly.petadoptionapp.ui.theme.PetAdoptionAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val loginViewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel.isLogin()
        loginViewModel.goToHome.observe(this){
            it?.let {
                if(it){
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }

            }
        }
        setContent {
            PetAdoptionAppTheme(darkTheme = false) {
                LoginScreen(loginViewModel)
            }
        }
    }
}