package com.estarly.petadoptionapp.ui.store

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.CustomFadeIn
import com.estarly.petadoptionapp.ui.CustomSlideDown
import com.estarly.petadoptionapp.ui.CustomSlideLeft
import com.estarly.petadoptionapp.ui.composables.CustomShimmerRectangleWait
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.composables.CustomSpaceWidth
import com.estarly.petadoptionapp.ui.composables.CustomTextWithIcon
import com.estarly.petadoptionapp.ui.model.ProductModel
import com.estarly.petadoptionapp.ui.theme.CardElevation
import com.estarly.petadoptionapp.ui.theme.MarginHorizontalScreen
import com.estarly.petadoptionapp.utils.format

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StoreScreen(storeViewModel: StoreViewModel) {
    val listProducts by storeViewModel.listProducts.observeAsState(initial = listOf())
    val wait by storeViewModel.showProgressProducts.observeAsState(initial = true)

    Scaffold {
        Box(
            modifier = Modifier.padding()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = MarginHorizontalScreen),
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                verticalArrangement = Arrangement.spacedBy(MarginHorizontalScreen)
            ) {
                this.header {
                    Header()
                }
                if(wait){
                    items(5) {
                        CustomShimmerRectangleWait(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(25.dp))
                                .height(160.dp)
                        )
                    }
                }else{
                    items(listProducts) {
                        ItemProduct(it)
                    }
                }
            }
        }
    }
}

@Composable
fun Header() {
    Column {
        CustomSpaceHeight(height = 25.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomSlideDown(delay = 100) {
                Text(
                    text = "Shop",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            CustomSlideLeft(delay = 150){
                Icon(painter = painterResource(id = R.drawable.ic_cart), contentDescription = "")
            }
        }
        CustomSpaceHeight(height = 20.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomSlideDown(delay = 200){
                Text(
                    text = "Pet foods",
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.onPrimary,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            CustomSlideDown(delay = 250){
                CustomTextWithIcon(
                    text = "sort",
                    icon = R.drawable.ic_sort,
                    iconAlignmentRight = false
                )
            }
            CustomSpaceWidth(width = 15.dp)
            CustomSlideDown(delay = 300) {
                CustomTextWithIcon(
                    text = "Filter",
                    icon = R.drawable.ic_filter,
                    iconAlignmentRight = false
                )
            }
        }
        CustomSpaceHeight(height = 20.dp)
    }
}

fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemProduct(product : ProductModel) {
    with(product) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardElevation,
            shape = RoundedCornerShape(25.dp),
            backgroundColor = Color.White,

            ) {
            CustomFadeIn(duration = 500, delay = 200){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
                ) {
                    GlideImage(
                        model = imageProduct,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(150.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = nameProduct,
                                fontSize = 15.sp,
                                color = MaterialTheme.colors.onPrimary,
                                fontWeight = FontWeight.Bold,
                                maxLines = 2
                            )
                            Text(
                                text = nameTypeProduct,
                                fontSize = 15.sp,
                                color = MaterialTheme.colors.onSecondary,
                                fontWeight = FontWeight.Bold,
                                maxLines = 2
                            )
                        }
                        Text(
                            text = "$${price.format()}",
                            fontSize = 15.sp,
                            color = MaterialTheme.colors.onPrimary,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        }
    }
}
