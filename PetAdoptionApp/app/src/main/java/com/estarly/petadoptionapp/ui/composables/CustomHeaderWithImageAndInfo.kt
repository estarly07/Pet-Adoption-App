package com.estarly.petadoptionapp.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material.icons.sharp.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.estarly.petadoptionapp.ui.theme.CardBackground
import com.estarly.petadoptionapp.ui.theme.TextColor
import com.estarly.petadoptionapp.ui.theme.TitleColor


@Composable
fun CustomHeaderWithImageAndInfo(image : String, title : String, subtitle: String, space : Dp){
    Header(image)
    CustomSpaceHeight(height = space)
    Info(title,subtitle)
}

@Composable
fun Info(title: String, subtitle: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = title,
                color = TitleColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = subtitle,
                color = TextColor,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        FloatingActionButton(
            onClick = {  },
            backgroundColor = CardBackground,
            elevation = FloatingActionButtonDefaults.elevation(0.dp)
        ) {
            Icon(imageVector = Icons.Sharp.Star, contentDescription = "icon like")
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Header(image: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(imageVector = Icons.Sharp.ArrowBack, contentDescription ="icon back" )
        GlideImage(
            model = image,
            contentDescription = "image",
            modifier = Modifier
                .weight(1f)
                .height(300.dp)
        )
        Icon(imageVector = Icons.Sharp.Menu, contentDescription ="icon menu" )
    }
}
