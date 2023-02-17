package com.estarly.petadoptionapp.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material.icons.sharp.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.estarly.petadoptionapp.R

@Composable
fun CustomHeaderWithImageAndInfo(image : String, title : String, subtitle: String, space : Dp){
    Header(image)
    CustomSpaceHeight(height = space)
    Info(title,subtitle)
}

@Composable
fun Info(title: String, subtitle: String) {
    val brush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colors.primary,
            MaterialTheme.colors.primaryVariant
        )
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = title,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = subtitle,
                color = MaterialTheme.colors.onSecondary,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        FloatingActionButton(
            onClick = {  },
            backgroundColor = MaterialTheme.colors.secondary,
            elevation = FloatingActionButtonDefaults.elevation(0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_love),
                contentDescription = "icon like",
                modifier = Modifier
                    .size(30.dp)
                    .graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(
                                brush= brush,
                                blendMode = BlendMode.SrcAtop)
                        }
                    },
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Header(image: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(painter = painterResource(id = R.drawable.ic_arrow_left), contentDescription ="icon back" )
        GlideImage(
            model = image,
            contentDescription = "image",
            modifier = Modifier
                .weight(1f)
                .height(300.dp)
        )
        Icon(painter = painterResource(id = R.drawable.ic_more), contentDescription ="icon menu" )
    }
}
