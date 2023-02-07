package com.estarly.petadoptionapp.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.composables.CustomCard
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.composables.CustomStaggeredVerticalGrid
import com.estarly.petadoptionapp.ui.composables.CustomTextField
import com.estarly.petadoptionapp.ui.model.BreedModel
import com.estarly.petadoptionapp.ui.model.PromotionModel
import com.estarly.petadoptionapp.ui.model.TagModel
import com.estarly.petadoptionapp.ui.theme.*
import java.util.*

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val showProgressPromotion: Boolean by homeViewModel.showProgressPromotion.observeAsState(initial = true)
    val promotion: PromotionModel? by homeViewModel.promotion.observeAsState(initial = null)
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = MarginHorizontalScreen)
    ) {
        CustomSpaceHeight(height = 25.dp)
        Header()
        CustomSpaceHeight(height = 25.dp)
        Search()
        CustomSpaceHeight(height = 25.dp)
        Tags(
            listOf(
                TagModel(id = 0, nameTag = "All"),
                TagModel(id = 1, nameTag = "Cats"),
                TagModel(id = 2, nameTag = "Dogs"),
                TagModel(id = 3, nameTag = "Birds"),
            )
        )
        CustomSpaceHeight(height = 25.dp)
        PromotionCard(promotion, showProgressPromotion)
        CustomSpaceHeight(height = 25.dp)
        TitleAndViewAll()
        CustomSpaceHeight(height = 20.dp)
        Pets(
            listOf(
                BreedModel(
                    breedName = "American Curl",
                    amount = 120,
                    image = "https://www.wikichat.fr/wp-content/uploads/sites/2/AMERICAN_CURL1.png"
                ),
                BreedModel(
                    breedName = "British Shorthair",
                    amount = 20,
                    image = "https://i.pinimg.com/originals/66/1a/13/661a136baa0e6631652ab6ae04a69bdf.png"
                ),
                BreedModel(
                    breedName = "English Cocker",
                    amount = 10,
                    image = "https://static.wixstatic.com/media/d8fd22_470f60f993a74654ab21d9b8a3f3be5f~mv2.png/v1/fill/w_535,h_573,al_c/d8fd22_470f60f993a74654ab21d9b8a3f3be5f~mv2.png"
                ),
            )
        )

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Pets(listBreeds: List<BreedModel>) {
    CustomStaggeredVerticalGrid(
        numColumns = 2,
    ) {
        listBreeds.forEachIndexed { i, breed ->
            with(breed) {
                CustomCard(
                    Modifier.padding(
                        start = if (i % 2 != 0) 10.dp else 0.dp,
                        end = if (i % 2 == 0) 10.dp else 0.dp,
                        bottom = 20.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = breedName,
                            color = TitleColor,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Start)
                        )
                        Text(
                            text = "$amount available",
                            color = TextColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            modifier = Modifier.align(Alignment.Start)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "image pet",
                            modifier = Modifier.fillMaxWidth()
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun TitleAndViewAll() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Top Breeds",
            color = TitleColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(text = "View all", color = TextColor, fontSize = 15.sp)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PromotionCard(promotion: PromotionModel?, showProgressPromotion: Boolean) {
    if (showProgressPromotion) {
        CircularProgressIndicator()
    } else if (!showProgressPromotion && promotion == null) {
        Box() { }
    } else {
        CustomCard {
            with(promotion!!) {
                Row(modifier = Modifier.padding(20.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            color = TitleColor,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Upto $percentage% off",
                            color = Purple,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = TextColor,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Use code ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Purple,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(code.uppercase(Locale.getDefault()))
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = TextColor,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(" and grab offer")
                            }
                        })
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "image promotion",
                        modifier = Modifier
                            .weight(1.5f)
                            .align(Alignment.Bottom)
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(start = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Tags(listTags: List<TagModel>) {
    LazyRow(content = {
        items(listTags, key = { it.id }) {
            ItemTag(
                tag = it,
                colorSelect = TitleColor,
                colorUnSelect = TextColor,
                modifier = Modifier.padding(end = 35.dp),
                isSelect = it.id == 0
            ) {

            }
        }
    })
}

@Composable
fun ItemTag(
    tag: TagModel,
    colorSelect: Color,
    colorUnSelect: Color,
    modifier: Modifier,
    isSelect: Boolean,
    onTap: (TagModel) -> Unit
) {
    with(tag) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.clickable { onTap(tag) })
        {
            Text(
                nameTag,
                color = if (isSelect) colorSelect else colorUnSelect,
                fontWeight = if (isSelect) FontWeight.Bold else null
            )
            if (isSelect) {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(colorSelect)
                )
            }
        }
    }
}

@Composable
fun Search() {
    Row {
        CustomTextField(
            value = "",
            onTextChanged = {},
            modifier = Modifier.weight(1f),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Icon search",
                    tint = TextColor

                )
            },
            textColor = TextColor,
            backgroundColor = CardBackground,
            placerHolder = "Search"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Pink,
                            Purple
                        )
                    )
                )
                .clickable {

                },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "button filter",
                tint = Color.White
            )
        }
    }
}

@Composable
fun Header() {
    Row {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Hi Bunny!",
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                color = TitleColor
            )
            Text(
                text = "Are you looking for pets?",
                fontSize = 15.sp,
                color = TextColor
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "user image",
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(15.dp))
        )
    }
}
