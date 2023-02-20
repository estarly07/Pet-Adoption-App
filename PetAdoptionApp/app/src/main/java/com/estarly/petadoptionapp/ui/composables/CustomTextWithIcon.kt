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
    iconAlignmentRight : Boolean = true,
    @DrawableRes icon : Int
){
    Row(modifier = modifier) {
        if(iconAlignmentRight) CustomIcon(icon,iconAlignmentRight)
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = if (bold) FontWeight.Bold else null,
            fontSize = 15.sp,
        )
        if(!iconAlignmentRight) CustomIcon(icon, iconAlignmentRight)
    }
}

@Composable
private fun CustomIcon(@DrawableRes icon: Int, iconAlignmentRight: Boolean, ) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = "icon",
        modifier = Modifier
            .size(20.dp)
            .padding(
                end = if(iconAlignmentRight) 5.dp else 0.dp,
                start = if(iconAlignmentRight) 0.dp else 5.dp,
            ),
        tint = MaterialTheme.colors.onSecondary
    )
}
