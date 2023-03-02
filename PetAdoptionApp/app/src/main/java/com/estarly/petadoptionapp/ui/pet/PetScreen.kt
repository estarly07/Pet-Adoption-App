package com.estarly.petadoptionapp.ui.pet

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
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
import androidx.navigation.NavHostController
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.CustomAnimateExpandBounce
import com.estarly.petadoptionapp.ui.CustomScaleIn
import com.estarly.petadoptionapp.ui.CustomSlideUp
import com.estarly.petadoptionapp.ui.composables.*
import com.estarly.petadoptionapp.domain.model.PetModel
import com.estarly.petadoptionapp.ui.theme.MarginHorizontalScreen
import com.estarly.petadoptionapp.utils.format
import com.estarly.petadoptionapp.utils.toYear

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PetScreen(
    pet: PetModel,
    breedName: String,
    petViewModel: PetViewModel,
    navigationController: NavHostController
) {
    val showMoreAbout by petViewModel.showMoreAbout.observeAsState(initial = false)
    Scaffold {
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
                    ){
                        navigationController.popBackStack()
                    }
                    CustomSpaceHeight(height = 25.dp)
                    About(about,showMoreAbout){ petViewModel.showMoreAbout()}
                    CustomSpaceHeight(height = 20.dp)
                    Info(months,address,sex,breedName)
                    CustomSpaceHeight(height = 25.dp)

                }
                CustomSlideUp(
                    delay = 150
                ){ AdoptButton(amount) }
            }
        }
    }
}

@Composable
fun AdoptButton(amount: Double) {
    Row {
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
            onClick = {},
        )
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
            onClick = {},
        )
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
            CustomScaleIn(
                delay = 150
            ){
                Text(
                    text = "${months.toYear()} years old",
                    color = MaterialTheme.colors.onSecondary,
                    fontSize = 15.sp,
                    maxLines = 7,
                )
            }
            CustomSpaceHeight(height = 5.dp)
            CustomScaleIn(
                delay = 250
            ){
                CustomTextWithIcon(
                    text = sex,
                    icon = R.drawable.ic_gender,
                    bold = false
                )
            }
        }
        CustomSpaceWidth(width = 35.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            CustomScaleIn(
                delay = 200
            ){
                CustomTextWithIcon(
                    text = address,
                    icon = R.drawable.ic_location,
                    bold = false
                )
            }
            CustomSpaceHeight(height = 5.dp)
            CustomScaleIn(
                delay = 300
            ){
                CustomTextWithIcon(
                    text = breedName,
                    icon = R.drawable.ic_pet,
                    bold = false
                )
            }
        }
    }
}

@Composable
fun About(about: String, showMoreAbout: Boolean, onClickMore: () -> Unit) {
    Column {
        CustomSlideUp(
            delay = 300
        ) {
            Text(
                text = "About",
                color = MaterialTheme.colors.onPrimary,
                fontSize = 17.sp,
                fontWeight = FontWeight.ExtraBold,
            )
        }
        CustomSpaceHeight(height = 5.dp)
        CustomSlideUp(
            delay = 350
        ){
            CustomAnimateExpandBounce {
                Text(
                    text = about,
                    color = MaterialTheme.colors.onSecondary,
                    fontSize = 15.sp,
                    maxLines = if (!showMoreAbout) 7 else Int.MAX_VALUE,
                    overflow = if (!showMoreAbout) TextOverflow.Ellipsis else TextOverflow.Visible,
                )
            }
        }
        CustomSlideUp(
            delay = 350
        ){
            Text(
                text = if (!showMoreAbout) "+More" else "-less",
                color = MaterialTheme.colors.primary,
                fontSize = 15.sp,
                modifier = Modifier.clickable { onClickMore() }
            )
        }
    }
}
