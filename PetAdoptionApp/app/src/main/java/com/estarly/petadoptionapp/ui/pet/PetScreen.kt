package com.estarly.petadoptionapp.ui.pet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.composables.*
import com.estarly.petadoptionapp.ui.model.PetModel
import com.estarly.petadoptionapp.ui.theme.MarginHorizontalScreen
import com.estarly.petadoptionapp.ui.theme.Purple
import com.estarly.petadoptionapp.ui.theme.TextColor
import com.estarly.petadoptionapp.ui.theme.TitleColor
import com.estarly.petadoptionapp.utils.format
import com.estarly.petadoptionapp.utils.toYear

@Composable
fun PetScreen(pet: PetModel, breedName: String, petViewModel: PetViewModel) {
    val showMoreAbout by petViewModel.showMoreAbout.observeAsState(initial = false)
    with(pet) {
        Column(
            Modifier.padding(
                start  = MarginHorizontalScreen,
                end    = MarginHorizontalScreen,
                bottom =  MarginHorizontalScreen,
            )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                CustomSpaceHeight(height = 25.dp)
                CustomHeaderWithImageAndInfo(
                    image = image,
                    title = namePet,
                    subtitle = breedName,
                    space = 15.dp
                )
                CustomSpaceHeight(height = 25.dp)
                About(about,showMoreAbout){ petViewModel.showMoreAbout()}
                CustomSpaceHeight(height = 20.dp)
                Info(months,address,sex,breedName)
                CustomSpaceHeight(height = 25.dp)

            }
            AdoptButton(amount)
        }
    }
}

@Composable
fun AdoptButton(amount: Double) {
    Row() {
        CustomButton(
            modifier = Modifier.height(50.dp),
            composable = {
                Text(
                    text = "$${amount.format()}",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 5.dp)
                )
            },
        ) {}
        CustomSpaceWidth(width = 10.dp)
        CustomButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            composable = {
                Text(
                    text = "Adopted",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 5.dp)
                )
            },
        ) {}
    }
}

@Composable
fun Info(months: Int, address: String, sex: String, breedName: String) {
    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = "${months.toYear()} years old",
                color = TextColor,
                fontSize = 15.sp,
                maxLines = 7,
            )
            CustomSpaceHeight(height = 5.dp)
            CustomTextWithIcon(
                text = sex,
                icon = R.drawable.ic_gender,
                bold = false
            )
        }
        CustomSpaceWidth(width = 35.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            CustomTextWithIcon(
                text = address,
                icon = R.drawable.ic_location,
                bold = false
            )
            CustomSpaceHeight(height = 5.dp)
            CustomTextWithIcon(
                text = breedName,
                icon = R.drawable.ic_pet,
                bold = false
            )
        }
    }
}

@Composable
fun About(about: String, showMoreAbout: Boolean, onClickMore: () -> Unit) {
    Column {
        Text(
            text = "About",
            color = TitleColor,
            fontSize = 17.sp,
            fontWeight = FontWeight.ExtraBold,
        )
        CustomSpaceHeight(height = 5.dp)
        Text(
            text = about,
            color = TextColor,
            fontSize = 15.sp,
            maxLines = if(!showMoreAbout) 7 else Int.MAX_VALUE,
            overflow = if(!showMoreAbout) TextOverflow.Ellipsis else TextOverflow.Visible,
        )
        Text(
            text = if(!showMoreAbout)"+More" else "-less",
            color = Purple,
            fontSize = 15.sp,
            modifier = Modifier.clickable { onClickMore() }
        )
    }
}
