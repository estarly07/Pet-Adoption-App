package com.estarly.petadoptionapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.CustomAnimateNumberUpOrDown

@Composable
fun CustomAddOrDismiss(
    cant       : Int,
    onAdd      : () -> Unit,
    onSubtract : ()-> Unit,
){
    Card(
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.secondary,
        modifier = Modifier.clip(RoundedCornerShape(5.dp))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White)
                    .clickable { onAdd() },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "",
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(5.dp)
                )
            }
            CustomAnimateNumberUpOrDown(count = cant){
                Text(
                    text = "$cant",
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White)
                    .clickable { onSubtract() },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_substract),
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(5.dp)
                )
            }
        }
    }
}