package com.estarly.petadoptionapp.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.estarly.petadoptionapp.ui.CustomFadeIn
import com.estarly.petadoptionapp.ui.CustomSlideLeft

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    wait: Boolean = false,
    height: Dp = 0.dp,
    content: @Composable () -> Unit,
){
    if(wait){
        CustomShimmerRectangleWait(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(25.dp))
                .height(height)
        )
    }else {
        CustomFadeIn(duration = 500, delay = 200){
            Card(
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.secondary,
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(25.dp)),
                content = content
            )
        }
    }
}