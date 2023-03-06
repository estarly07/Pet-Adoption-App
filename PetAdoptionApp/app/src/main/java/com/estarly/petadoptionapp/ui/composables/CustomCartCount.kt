package com.estarly.petadoptionapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.CustomAnimateNumberUpOrDown

@Composable
fun CustomCartCount(
    modifier: Modifier = Modifier,
    cant : Int
){
    ConstraintLayout() {
        val (icon,count) = createRefs()
        Icon(
            painter = painterResource(id = R.drawable.ic_cart),
            contentDescription = "",

            modifier = modifier
                .size(25.dp)
                .constrainAs(icon) {
                }
        )
        if(cant > 0) {

            Box(
                modifier = modifier
                    .defaultMinSize(minHeight = 15.dp, minWidth = 15.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primaryVariant)
                    .constrainAs(count) {
                        end.linkTo(icon.end)
                    }
            ) {
                Box(modifier = Modifier.align(Alignment.Center)){
                    CustomAnimateNumberUpOrDown(count = cant) {
                        Text(
                            text = "$cant",
                            color = Color.White,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .padding(1.dp)
                               ,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}