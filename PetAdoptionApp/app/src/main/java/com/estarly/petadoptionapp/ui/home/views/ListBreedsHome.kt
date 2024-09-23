package com.estarly.petadoptionapp.ui.home.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.estarly.petadoptionapp.domain.model.BreedModel
import com.estarly.petadoptionapp.ui.composables.CustomCard
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.composables.CustomStaggeredVerticalGrid


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BreedsHome(breeds: List<BreedModel>?, wait: Boolean, onClick: (BreedModel) -> Unit) {
    if (wait) {
        CustomStaggeredVerticalGrid(
            numColumns = 2,
        ) {
            listOf(170, 240, 200, 150, 200).forEachIndexed { i, size ->
                CustomCard(
                    modifier = Modifier.padding(
                        start = if (i % 2 != 0) 10.dp else 0.dp,
                        end = if (i % 2 == 0) 10.dp else 0.dp,
                        bottom = 20.dp
                    ),
                    wait = wait,
                    height = size.dp
                ) {}
            }
        }
        return
    }
    breeds?.let { listBreeds ->
        CustomStaggeredVerticalGrid(
            numColumns = 2,
        ) {
            listBreeds.forEachIndexed { i, breed ->
                with(breed) {
                    CustomCard(
                        Modifier
                            .padding(
                                start = if (i % 2 != 0) 10.dp else 0.dp,
                                end = if (i % 2 == 0) 10.dp else 0.dp,
                                bottom = 20.dp
                            )
                            .clickable {
                                onClick(breed)
                            },
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(15.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = breedName,
                                    color = MaterialTheme.colors.onPrimary,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    modifier = Modifier.align(Alignment.Start)
                                )
                                Text(
                                    text = "$amount available",
                                    color = MaterialTheme.colors.onSecondary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    modifier = Modifier.align(Alignment.Start)
                                )
                                CustomSpaceHeight(height = 5.dp)
                                GlideImage(
                                    model = image,
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
