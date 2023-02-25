package com.estarly.petadoptionapp.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.estarly.petadoptionapp.ui.theme.PetAdoptionAppTheme

class LoginActivity : ComponentActivity() {
    private val loginViewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetAdoptionAppTheme(darkTheme = false) {
                LoginScreen(loginViewModel)
            }
        }
    }
}