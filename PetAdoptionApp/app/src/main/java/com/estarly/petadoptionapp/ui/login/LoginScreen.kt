package com.estarly.petadoptionapp.ui.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Email
import androidx.compose.material.icons.sharp.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.composables.CustomButton
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.composables.CustomTextField
import com.estarly.petadoptionapp.ui.theme.MarginHorizontalScreen
import java.util.*

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Header(modifier = Modifier.weight(1f),R.drawable.dog)
        Body(modifier = Modifier.weight(1.2f),loginViewModel)
    }
}

@Composable
fun Body(modifier: Modifier,loginViewModel :LoginViewModel) {
    val email by loginViewModel.email.observeAsState(initial = "")
    val pass by loginViewModel.pass.observeAsState(initial = "")
    val showProgressButton by loginViewModel.showProgressLogin.observeAsState(initial = false)
    val errorEmail by loginViewModel.errorEmptyEmail.observeAsState(initial = "")
    val errorPass by loginViewModel.errorEmptyPass.observeAsState(initial = "")
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
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = MarginHorizontalScreen)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            CustomSpaceHeight(height = 25.dp)
            Text(
                text = "Welcome!!",
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Sign to continue",
                fontSize = 15.sp,
                color = Color.White,
            )
            CustomSpaceHeight(height = 25.dp)
            CustomTextField(
                value = email,
                showError = errorEmail.isNotEmpty(),
                error = errorEmail,
                modifier = Modifier.fillMaxWidth(),
                onTextChanged = {loginViewModel.changeTextEmail(it) },
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
            CustomSpaceHeight(height = 10.dp)
            CustomTextField(
                value = pass,
                modifier = Modifier.fillMaxWidth(),
                onTextChanged = {loginViewModel.changeTextPass(it) },
                showError = errorPass.isNotEmpty(),
                error = errorPass,
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
            CustomSpaceHeight(height = 20.dp)
            CustomButton(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                composable = {
                    Text(
                        text = "Login",
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
                loginViewModel.login()
            }
            CustomSpaceHeight(height = 20.dp)
            Text(
                modifier= Modifier.align(Alignment.CenterHorizontally).clickable {
                    loginViewModel.showRegisterScreen()
                },
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 15.sp,
                            color = Color.White,
                        )
                    ) {
                        append("Don't have an account? ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 15.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Sign up")
                    }
                })
            CustomSpaceHeight(height = 20.dp)
            Divider(color = Color.White)
            CustomSpaceHeight(height = 15.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
            }
        }
    }
}
