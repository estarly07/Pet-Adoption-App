package com.estarly.petadoptionapp.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.estarly.petadoptionapp.ui.theme.CardBackground

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    content : @Composable ()->Unit,
){
    Card(
        elevation = 0.dp,
        backgroundColor = CardBackground,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp)),
        content = content
    )
}