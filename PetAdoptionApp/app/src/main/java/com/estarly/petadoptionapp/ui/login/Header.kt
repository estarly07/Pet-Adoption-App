package com.estarly.petadoptionapp.ui.login

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun Header(
    modifier: Modifier,
    @DrawableRes image : Int
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "",
        modifier = modifier.fillMaxWidth(),
        alignment = Alignment.BottomCenter

    )
}