package com.estarly.petadoptionapp.ui.payment

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowForward
import androidx.compose.material.icons.sharp.CheckCircle
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
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.CustomSlideDown
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.composables.CustomTextWithIcon
import com.estarly.petadoptionapp.ui.theme.MarginHorizontalScreen

@Preview
@Composable
fun Preview(){ PaymentScreen() }

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PaymentScreen(){
    Scaffold() {
        Column{
            CustomSpaceHeight(height = 25.dp)
            Toolbar()
            CustomSpaceHeight(height = 25.dp)
            Box(modifier = Modifier.weight(1f)) {
                SelectPaymentMethod()
            }
            PaymentResume()
        }
    }
}
@Composable
fun Toolbar() {
    Row(
        modifier = Modifier.padding(horizontal = MarginHorizontalScreen),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        CustomSlideDown(
            delay = 100
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "icon back",
                modifier = Modifier.clickable { })
        }
        CustomSlideDown(
            delay = 150
        ){
            Text(
                text = "Payment Methods",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Composable
fun SelectPaymentMethod() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(horizontal = MarginHorizontalScreen)
    ){
        itemsIndexed(listOf(1,2,3,44,4,4,4,4,4)){i, item->
            OutlinedButton(
                onClick = { },
                border = BorderStroke(
                    if (i == 0) 2.dp else 0.dp,
                    if (i == 0) MaterialTheme.colors.primaryVariant else Color.Transparent,
                ),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(if(i == 0) Color.White else MaterialTheme.colors.secondary,)
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Column {
                        Text(
                            text = "Mastercard",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                        Text(text = "sdf")
                    }
                    if (i == 0) Icon(imageVector = Icons.Sharp.CheckCircle, contentDescription = "")
                }
            }
        }
    }
}

@Composable
fun PaymentResume() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp))
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                ),
                shape = RoundedCornerShape(50)
            ) {
                CustomTextWithIcon(text = "Pay", icon = R.drawable.ic_arrow_left, iconAlignmentRight = false)
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(text = "$ 359", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 18.sp)
                Text(text = "view Details",color = Color.White, fontSize = 15.sp)
            }
        }
    }
}
