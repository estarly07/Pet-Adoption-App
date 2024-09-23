package com.estarly.petadoptionapp.ui.home.views

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.domain.model.UserModel
import com.estarly.petadoptionapp.ui.CustomSlideDown
import com.estarly.petadoptionapp.ui.CustomSlideLeft
import com.estarly.petadoptionapp.utils.fontDimensionResource

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HeaderHome(context : Context,user: UserModel?) {
    Row {
        Column(modifier = Modifier.weight(1f)) {
            CustomSlideDown(delay = 100){
                Text(text = context.getString(R.string.greeting, user?.name ?: ""), fontSize =  fontDimensionResource(id = R.dimen.titleScreen), fontWeight = FontWeight.Bold,color = MaterialTheme.colors.onPrimary)
            }
            CustomSlideDown(delay = 150 ){
                Text(text = context.getString(R.string.are_you_looking_for_pets), fontSize = fontDimensionResource(id = R.dimen.subtitle), color = MaterialTheme.colors.onSecondary, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.width(15.dp))
        CustomSlideLeft(200){
            GlideImage(
                model        = user?.avatar,
                modifier     = Modifier.size(50.dp).clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = "user image",
            )
        }
    }
}