package com.estarly.petadoptionapp.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Check
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.composables.CustomAddOrDismiss
import com.estarly.petadoptionapp.ui.composables.CustomButton
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.theme.MarginHorizontalScreen
import com.estarly.petadoptionapp.ui.theme.PetAdoptionAppTheme
import com.estarly.petadoptionapp.utils.format

@Composable
fun CartScreen() {
    Column(
        modifier = Modifier.padding(horizontal = MarginHorizontalScreen)
    ) {
        CustomSpaceHeight(height = 25.dp)
        Header()
        CustomSpaceHeight(height = 25.dp)
        Products(modifier = Modifier.weight(1f))
        CustomSpaceHeight(height = 20.dp)
        Footer()
    }
}

@Composable
fun Footer() {
    Column( modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Max)
        .padding(
            bottom = MarginHorizontalScreen
        ),){
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.onSecondary)
        )
        CustomSpaceHeight(height = 10.dp)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Total", color = MaterialTheme.colors.onSecondary, fontSize = 15.sp)
            Text(
                text = "$${503.68.format()}",
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
        CustomSpaceHeight(height = 10.dp)
        CustomButton(modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
            composable = {
                Text(
                    text = "Proceed to Checkout",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 5.dp)
                )
        }){}
    }
}

@Composable
fun Products(modifier: Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(50) {
            ItemCart()
        }
    }
}

@Composable
fun ItemCart() {
    Card(
        elevation = 5.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(25.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier.size(100.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "nameTypeProduct",
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
                CustomAddOrDismiss(
                    1,
                    {  },
                    {}
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_like), contentDescription = "")
                Text(
                    text = "$5.10",
                    color = MaterialTheme.colors.primary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = "icon back",
            modifier = Modifier.clickable {})
        Text(
            text = "My Order",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = MaterialTheme.colors.onPrimary
        )
        Icon(
            imageVector = Icons.Sharp.Delete,
            contentDescription = "icon back",
            modifier = Modifier.clickable {})
    }
}
