package com.estarly.petadoptionapp.ui.login.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.estarly.petadoptionapp.ui.CustomFadeIn
import com.estarly.petadoptionapp.ui.CustomSlideUp

@Composable
fun Header(modifier: Modifier,@DrawableRes image : Int) {
    Box(modifier = modifier){
        CustomSlideUp(delay = 50) {
            CustomFadeIn( duration = 500) {
                Image(
                    painter            = painterResource(id = image),
                    contentDescription = "",
                    modifier           = Modifier.fillMaxSize(),
                    alignment          = Alignment.BottomCenter
                )
            }
        }
    }
}