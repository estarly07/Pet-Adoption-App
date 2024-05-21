package com.estarly.petadoptionapp.ui.login.screens

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.login.viewmodels.LoginViewModel
import com.estarly.petadoptionapp.ui.login.views.BodyRegister
import com.estarly.petadoptionapp.ui.login.views.Header
import com.estarly.petadoptionapp.ui.theme.MarginHorizontalScreen

@Composable
fun RegisterScreen(context: Context,loginViewModel: LoginViewModel) {
    Column {
        CustomSpaceHeight(height = 15.dp)
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription ="icon back",
            modifier = Modifier
                .padding(start = MarginHorizontalScreen)
                .clickable {loginViewModel.showLoginScreen()} )
        CustomSpaceHeight(height = 15.dp)
        Header(modifier = Modifier.weight(1f),R.drawable.dog_register)
        BodyRegister(context = context, modifier = Modifier.weight(2f),loginViewModel)
    }
}