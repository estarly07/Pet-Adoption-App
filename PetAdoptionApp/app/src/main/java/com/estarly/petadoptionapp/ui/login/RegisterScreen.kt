package com.estarly.petadoptionapp.ui.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Email
import androidx.compose.material.icons.sharp.Lock
import androidx.compose.material.icons.sharp.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.composables.CustomButton
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.composables.CustomTextField
import com.estarly.petadoptionapp.ui.theme.MarginHorizontalScreen

@Composable
fun RegisterScreen(loginViewModel: LoginViewModel) {
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
        Body(modifier = Modifier.weight(2f),loginViewModel)
    }
}

@Composable
private fun Body(modifier: Modifier, loginViewModel: LoginViewModel) {
    val email by loginViewModel.emailRegister.observeAsState(initial = "")
    val pass by loginViewModel.passRegister.observeAsState(initial = "")
    val name by loginViewModel.nameRegister.observeAsState(initial = "")
    val showProgressButton by loginViewModel.showProgressRegister.observeAsState(initial = false)
    val errorEmail by loginViewModel.errorEmailRegister.observeAsState(initial = "")
    val errorPass by loginViewModel.errorPassRegister.observeAsState(initial = "")
    val errorName by loginViewModel.errorNameRegister.observeAsState(initial = "")
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topEnd = 25.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.primaryVariant,
                        MaterialTheme.colors.primary,
                    )
                )
            )
    ){
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = MarginHorizontalScreen)
        ) {
            CustomSpaceHeight(height = 35.dp)
            Text(
                text = "Create Account",
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            CustomSpaceHeight(height = 25.dp)
            CustomTextField(
                value = name,
                modifier = Modifier.fillMaxWidth(),
                onTextChanged = {loginViewModel.changeTextNameRegister(it) },
                error = errorName,
                showError = errorName.isNotEmpty(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Sharp.Person,
                        contentDescription = "Icon name",
                        tint = MaterialTheme.colors.onSecondary
                    )
                },
                textColor = MaterialTheme.colors.onSecondary,
                backgroundColor = MaterialTheme.colors.secondary,
                placerHolder = "Full name"
            )
            CustomSpaceHeight(height = 15.dp)
            CustomTextField(
                value = email,
                modifier = Modifier.fillMaxWidth(),
                onTextChanged = {loginViewModel.changeTextEmailRegister(it) },
                error = errorEmail,
                showError = errorEmail.isNotEmpty(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Sharp.Email,
                        contentDescription = "Icon email",
                        tint = MaterialTheme.colors.onSecondary
                    )
                },
                textColor = MaterialTheme.colors.onSecondary,
                backgroundColor = MaterialTheme.colors.secondary,
                placerHolder = "Email"
            )
            CustomSpaceHeight(height = 15.dp)
            CustomTextField(
                value = pass,
                modifier = Modifier.fillMaxWidth(),
                onTextChanged = {loginViewModel.changeTextPassRegister(it) },
                error = errorPass,
                showError = errorPass.isNotEmpty(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Sharp.Lock,
                        contentDescription = "Icon pass",
                        tint = MaterialTheme.colors.onSecondary
                    )
                },
                textColor = MaterialTheme.colors.onSecondary,
                backgroundColor = MaterialTheme.colors.secondary,
                placerHolder = "Password"
            )
            CustomSpaceHeight(height = 35.dp)
            CustomButton(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                composable = {
                    Text(
                        text = "Register",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 5.dp)
                    )
                },
                wait = showProgressButton,
                color = MaterialTheme.colors.primary.copy(blue = 0.8f)
            ) {
                loginViewModel.register()
            }
        }
    }
}