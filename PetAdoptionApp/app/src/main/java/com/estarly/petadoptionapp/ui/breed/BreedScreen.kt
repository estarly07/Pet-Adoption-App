package com.estarly.petadoptionapp.ui.breed

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.composables.CustomCard
import com.estarly.petadoptionapp.ui.composables.CustomHeaderWithImageAndInfo
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.composables.CustomTextWithIcon
import com.estarly.petadoptionapp.ui.model.toJson
import com.estarly.petadoptionapp.ui.navigators.Route
import com.estarly.petadoptionapp.ui.theme.MarginHorizontalScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun BreedScreen(
    nameBreed: String,
    image: String,
    breedViewModel: BreedViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    val pets by breedViewModel.pets.observeAsState(initial = listOf())
    val showProgressPets by breedViewModel.showProgressPets.observeAsState(initial = true)

    Scaffold {
        Column(
            modifier = Modifier
                .padding(horizontal = MarginHorizontalScreen)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                this.header {
                    Column {
                        CustomSpaceHeight(height = 25.dp)
                        CustomHeaderWithImageAndInfo(
                            image = image,
                            title = nameBreed,
                            subtitle = "${pets.size} pets",
                            space = 5.dp
                        ){
                            (context as Activity).onBackPressed()
                        }
                        CustomSpaceHeight(height = 15.dp)
                    }
                }
                if (showProgressPets) {
                    items(5){
                        CustomCard(
                            wait   = showProgressPets,
                            height = 200.dp
                        ){}
                    }
                } else {

                    items(pets, key = {it.idPet}) { pet ->
                        CustomCard(
                            Modifier
                                .height(200.dp)
                                .animateItemPlacement()
                                .clickable {
                                    navController.navigate(Route.ScreenPet.createRoute(breedName = nameBreed, pet =  pet.toJson()))
                                },
                            content = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(15.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = pet.namePet,
                                        color = MaterialTheme.colors.onPrimary,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        modifier = Modifier.align(Alignment.Start)
                                    )
                                    CustomTextWithIcon(
                                        modifier = Modifier.align(Alignment.Start),
                                        text = pet.sex,
                                        icon = R.drawable.ic_pet
                                    )
                                    CustomSpaceHeight(height = 5.dp)
                                    GlideImage(
                                        model = pet.image,
                                        contentDescription = "image pet",
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}
