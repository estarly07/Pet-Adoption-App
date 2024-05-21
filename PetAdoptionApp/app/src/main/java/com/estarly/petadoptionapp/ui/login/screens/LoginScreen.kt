package com.estarly.petadoptionapp.ui.login.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.login.viewmodels.LoginViewModel
import com.estarly.petadoptionapp.ui.login.views.BodyLogin
import com.estarly.petadoptionapp.ui.login.views.Header

@Composable
fun LoginScreen(context : Context, loginViewModel: LoginViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Header   (modifier = Modifier.weight(1f),R.drawable.dog)
        BodyLogin(context = context, modifier = Modifier.weight(1.2f),loginViewModel)
    }
}

