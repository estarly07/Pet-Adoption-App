package com.estarly.petadoptionapp.ui.payment

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material.icons.sharp.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.domain.model.SelectPaymentModel
import com.estarly.petadoptionapp.ui.CustomSlideDown
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.composables.CustomTextWithIcon
import com.estarly.petadoptionapp.ui.theme.MarginHorizontalScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PaymentScreen(selectPaymentViewModel:SelectPaymentViewModel){
    val listPayments by selectPaymentViewModel.listPayments.observeAsState(initial = listOf())
    Scaffold() {
        Column{
            CustomSpaceHeight(height = 25.dp)
            Toolbar()
            CustomSpaceHeight(height = 25.dp)
            Box(modifier = Modifier.weight(1f)) {
                SelectPaymentMethod(listPayments){
                    selectPaymentViewModel.checkPayment(it)
                }
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
fun SelectPaymentMethod(listPayments: List<SelectPaymentModel>, onCheck : (SelectPaymentModel)->Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(horizontal = MarginHorizontalScreen)
    ){
        itemsIndexed(listPayments){i, item->
            OutlinedButton(
                onClick = {onCheck(item)},
                border = BorderStroke(
                    if (item.isCheck) 2.dp else 0.dp,
                    if (item.isCheck) MaterialTheme.colors.primaryVariant else Color.Transparent,
                ),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(if (item.isCheck) Color.White else MaterialTheme.colors.secondary,)
            ){
                with(item.paymentModel){
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Column {
                            Text(
                                text = type,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.ExtraBold,
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id =
                                        when(type){
                                            "Visa"-> R.drawable.visa
                                            else  -> R.drawable.mastercard
                                        }

                                    ),
                                    modifier = Modifier.size(40.dp),
                                    contentDescription = ""
                                )
                                Box(modifier = Modifier.width(5.dp))
                                Text(text = "${"*".repeat(number.length-4)}${number.takeLast(4)}")
                            }
                        }
                        if (item.isCheck) Icon(imageVector = Icons.Sharp.CheckCircle, contentDescription = "", modifier = Modifier.size(25.dp))
                    }
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
