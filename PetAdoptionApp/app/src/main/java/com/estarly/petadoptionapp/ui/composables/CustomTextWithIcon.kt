package com.estarly.petadoptionapp.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextWithIcon(
    bold     : Boolean = true,
    modifier : Modifier = Modifier,
    text     : String,
    @DrawableRes icon : Int
){
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "icon",
            modifier = Modifier
                .size(20.dp)
                .padding(end = 5.dp),
            tint = MaterialTheme.colors.onSecondary
        )
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = if (bold) FontWeight.Bold else null,
            fontSize = 15.sp,
        )
    }
}