package com.estarly.petadoptionapp.ui.home.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.estarly.petadoptionapp.ui.CustomSlideLeft
import com.estarly.petadoptionapp.ui.CustomSlideRight


@Composable
fun TitleAndViewAllHome() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomSlideRight(delay = 350){
            Text(
                text = "Top Breeds",
                color = MaterialTheme.colors.onPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        CustomSlideLeft(400){
            Text(
                text = "View all",
                color = MaterialTheme.colors.onSecondary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}