package com.estarly.petadoptionapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estarly.petadoptionapp.R

@Composable
private  fun TrailingIcon(passwordVisible: Boolean, onTap : () -> Unit) {
    IconButton(onClick = {onTap()}) {
        Image(
            painter =  painterResource(if (passwordVisible) R.drawable.ic_visible_password else R.drawable.ic_novisible_password),
            contentDescription = if (passwordVisible) "Hide password" else "Show password",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSecondary)
        )
    }
}
@Composable
fun CustomTextField(
    value          : String,
    onTextChanged  : (String) -> Unit,
    modifier       : Modifier = Modifier,
    leadingIcon    : @Composable (()->Unit)?,
    placerHolder   : String,
    textColor      : Color,
    showError      : Boolean = false,
    error          : String = "",
    backgroundColor: Color,
    isPassword     : Boolean = false
) {
    var passwordVisible  by remember { mutableStateOf(false) }
    Column{
        TextField(
            value         = value,
            onValueChange = onTextChanged,
            modifier      = modifier
                .clip(RoundedCornerShape(15.dp))
                .size(50.dp),
            leadingIcon     = leadingIcon,
            placeholder     = { Text(text = placerHolder, fontWeight = FontWeight.SemiBold) },
            visualTransformation = if (passwordVisible || !isPassword)  VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon    = {
                if(!isPassword) return@TextField
                TrailingIcon(passwordVisible) {
                    passwordVisible = !passwordVisible
                }
            },
            maxLines        = 1,
            singleLine      = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.textFieldColors(
                textColor               = textColor,
                placeholderColor        = textColor,
                backgroundColor         = backgroundColor,
                focusedIndicatorColor   = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )

        )
        if(showError)
            Box(modifier = Modifier
                .align(Alignment.End)
                .padding(top = 5.dp)){
                Text(
                    text       = error,
                    color      = Color.Red,
                    fontSize   = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
    }
}