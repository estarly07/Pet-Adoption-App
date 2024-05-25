package com.estarly.petadoptionapp.ui.login.views

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.composables.CustomButton
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.composables.CustomTextField
import com.estarly.petadoptionapp.ui.login.viewmodels.LoginViewModel
import com.estarly.petadoptionapp.ui.theme.MarginHorizontalScreen
import com.estarly.petadoptionapp.utils.fontDimensionResource

@Composable
fun BodyLogin(context: Context, modifier: Modifier, loginViewModel : LoginViewModel,) {
    val email                by loginViewModel.email.observeAsState(initial = "")
    val pass                 by loginViewModel.pass.observeAsState(initial = "")
    val showProgressButton by loginViewModel.showProgressLogin.observeAsState(initial = false)
    val errorEmail           by loginViewModel.errorEmail.observeAsState(initial = "")
    val errorPass            by loginViewModel.errorPass.observeAsState(initial = "")
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
                .verticalScroll(rememberScrollState())
        ) {
            CustomSpaceHeight(height = 25.dp)
            Text(text = context.getString(R.string.welcome), fontSize = fontDimensionResource(id = R.dimen.titleScreen), color = Color.White,fontWeight = FontWeight.Bold)
            Text(text = context.getString(R.string.sign_to_continue), fontSize = fontDimensionResource(id = R.dimen.subtitle), color = Color.White,)
            CustomSpaceHeight(height = 25.dp)
            CustomTextField(
                value         = email,
                showError     = errorEmail.isNotEmpty(),
                error         = errorEmail,
                modifier      = Modifier.fillMaxWidth(),
                onTextChanged = {loginViewModel.changeTextEmail(it) },
                leadingIcon   = {
                    Icon(
                        imageVector        = Icons.Sharp.Email,
                        contentDescription = "Icon email",
                        tint               = MaterialTheme.colors.onSecondary
                    )
                },
                textColor       = MaterialTheme.colors.onSecondary,
                backgroundColor = MaterialTheme.colors.secondary,
                placerHolder    = context.getString(R.string.email)
            )
            CustomSpaceHeight(height = 10.dp)
            CustomTextField(
                value         = pass,
                modifier      = Modifier.fillMaxWidth(),
                onTextChanged = {loginViewModel.changeTextPass(it) },
                showError     = errorPass.isNotEmpty(),
                error         = errorPass,
                isPassword    = true,
                leadingIcon   = {
                    Icon(
                        imageVector        = Icons.Sharp.Lock,
                        contentDescription = "Icon pass",
                        tint               = MaterialTheme.colors.onSecondary
                    )
                },
                textColor       = MaterialTheme.colors.onSecondary,
                backgroundColor = MaterialTheme.colors.secondary,
                placerHolder    = context.getString(R.string.password)
            )
            CustomSpaceHeight(height = 20.dp)
            CustomButton(
                modifier   = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                wait       = showProgressButton,
                color      = MaterialTheme.colors.primary.copy(blue = 0.8f),
                composable = {
                    Text(
                        text       = context.getString(R.string.login),
                        color      = Color.White,
                        fontSize   = fontDimensionResource(id = R.dimen.subtitle),
                        fontWeight = FontWeight.Bold,
                        modifier   = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 5.dp)
                    )
                },
            ) {
                loginViewModel.login(context = context)
            }
            CustomSpaceHeight(height = 20.dp)
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { loginViewModel.showRegisterScreen() },
                text     = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(fontSize = fontDimensionResource(id = R.dimen.normal), color = Color.White,)
                    ) {
                        append(context.getString(R.string.do_not_have_an_account))
                        append(" ")
                    }
                    withStyle(
                        style = SpanStyle(fontSize = fontDimensionResource(id = R.dimen.subtitle), color = Color.White, fontWeight = FontWeight.Bold)
                    ) {
                        append(context.getString(R.string.sign_up))
                    }
                })
            CustomSpaceHeight(height = 20.dp)
            Divider(color = Color.White)
            CustomSpaceHeight(height = 15.dp)
            SocialIcons { loginViewModel.showDialogGoogle() }
        }
    }
}
