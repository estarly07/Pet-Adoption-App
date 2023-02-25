package com.estarly.petadoptionapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    modifier: Modifier,
    isCircle: Boolean = false,
    composable: @Composable() (BoxScope.() -> Unit),
    color: Color? = null,
    wait : Boolean = false,
    onClick: () -> Unit,
){
    if(wait){
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            CircularProgressIndicator(
                color = color ?: MaterialTheme.colors.primary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        return
    }
    Box(
        modifier = modifier
            .clip(if (isCircle) CircleShape else RoundedCornerShape(15.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors =
                    if (color != null) {
                        listOf(
                            color,
                            color,
                        )
                    } else {
                        listOf(
                            MaterialTheme.colors.primary,
                            MaterialTheme.colors.primaryVariant
                        )
                    }
                )
            )
            .clickable { onClick() },
        content = composable
    )
}