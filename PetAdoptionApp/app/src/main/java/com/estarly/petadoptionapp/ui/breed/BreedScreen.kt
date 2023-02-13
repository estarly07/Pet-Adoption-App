package com.estarly.petadoptionapp.ui.breed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.composables.CustomCard
import com.estarly.petadoptionapp.ui.composables.CustomHeaderWithImageAndInfo
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.theme.MarginHorizontalScreen
import com.estarly.petadoptionapp.ui.theme.TextColor
import com.estarly.petadoptionapp.ui.theme.TitleColor

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BreedScreen(
    idBreed: Int,
    nameBreed: String,
    image: String,
    breedViewModel: BreedViewModel
) {
    val pets by breedViewModel.pets.observeAsState(initial = listOf())
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
                    )
                    CustomSpaceHeight(height = 15.dp)
                }
            }
            items(pets) { pet ->
                CustomCard(
                    Modifier
                        .height(200.dp)
                        .clickable {}
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = pet.namePet,
                            color = TitleColor,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.align(Alignment.Start)
                        )
                        Row(Modifier.align(Alignment.Start)) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_pet),
                                contentDescription = "icon pet",
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(end = 5.dp),
                                tint = TextColor
                            )
                            Text(
                                text = pet.sex,
                                color = TextColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                            )
                        }
                        CustomSpaceHeight(height = 5.dp)
                        GlideImage(
                            model = pet.image,
                            contentDescription = "image pet",
                            modifier = Modifier.fillMaxWidth()
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
