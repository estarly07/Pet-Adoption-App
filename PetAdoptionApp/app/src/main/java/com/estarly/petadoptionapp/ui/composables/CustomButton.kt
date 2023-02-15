package com.estarly.petadoptionapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.estarly.petadoptionapp.ui.theme.Pink
import com.estarly.petadoptionapp.ui.theme.Purple

@Composable
fun CustomButton(
    modifier: Modifier,
    composable: @Composable BoxScope.() -> Unit,
    onClick : ()->Unit,
){
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Pink,
                        Purple
                    )
                )
            )
            .clickable { onClick() },
        content = composable
    )
}