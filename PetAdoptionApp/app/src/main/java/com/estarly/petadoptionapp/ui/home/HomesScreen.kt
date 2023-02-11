package com.estarly.petadoptionapp.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.composables.CustomCard
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.composables.CustomStaggeredVerticalGrid
import com.estarly.petadoptionapp.ui.composables.CustomTextField
import com.estarly.petadoptionapp.ui.dialog.filter.CustomDialogFilter
import com.estarly.petadoptionapp.ui.model.BreedModel
import com.estarly.petadoptionapp.ui.model.PromotionModel
import com.estarly.petadoptionapp.ui.model.CategoryModel
import com.estarly.petadoptionapp.ui.theme.*
import java.util.*

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val showProgressPromotion: Boolean by homeViewModel.showProgressPromotion.observeAsState(initial = true)
    val promotion: PromotionModel? by homeViewModel.promotion.observeAsState(initial = null)
    val breeds: List<BreedModel>? by homeViewModel.breeds.observeAsState(initial = null)
    val search: String by homeViewModel.search.observeAsState(initial = "")
    val isSearching : Boolean by homeViewModel.isSearching.observeAsState(initial = true)
    val showDialogFilter : Boolean by homeViewModel.showDialogFilter.observeAsState(initial = false)
    val idSelectTag : Int by homeViewModel.idSelectTag.observeAsState(initial = 0)
    val tags : List<CategoryModel> by homeViewModel.tags.observeAsState(initial = listOf())
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = MarginHorizontalScreen)
    ) {
        CustomSpaceHeight(height = 25.dp)
        Header()
        CustomSpaceHeight(height = 25.dp)
        Search(search, onClickFilter = {homeViewModel.showDialogFilter()}){ homeViewModel.searchBread(it) }
        CustomSpaceHeight(height = 28.dp)
        if(isSearching) {
            Tags(tags,idSelectTag){id-> homeViewModel.changeSelectTag(id)}
            CustomSpaceHeight(height = 20.dp)
            PromotionCard(promotion, showProgressPromotion)
            CustomSpaceHeight(height = 20.dp)
            TitleAndViewAll()
            CustomSpaceHeight(height = 17.dp)
        }
        Pets(breeds)
        //Dialogs
        CustomDialogFilter(
            items = listOf("Nombre", "Cantidad"),
            categories = tags,
            show = showDialogFilter,
            onDismiss = { homeViewModel.showDialogFilter(false) }
        ) { attribute, idCategory ->
            homeViewModel.filter(attribute, idCategory)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Pets(breeds: List<BreedModel>?) {
    breeds?.let { listBreeds ->
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
                                fontSize = 13.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.align(Alignment.Start)
                            )
                            Text(
                                text = "$amount available",
                                color = TextColor,
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
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = "View all", color = TextColor, fontSize = 13.sp,fontWeight = FontWeight.Bold)
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
                Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            color = TitleColor,
                            fontSize = 17.sp
                        )
                        CustomSpaceHeight(height = 5.dp)
                        Text(
                            text = "Upto $percentage% off",
                            color = Purple,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        CustomSpaceHeight(height = 5.dp)
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = TextColor,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Use code ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Purple,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(code.uppercase(Locale.getDefault()))
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = TextColor,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(" and grab offer")
                            }
                        })
                    }
                    GlideImage(
                        model = image,
                        contentDescription = "image promotion",
                        modifier = Modifier
                            .weight(1.5f)
                            .align(Alignment.Bottom)
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(start = 10.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
    }
}

@Composable
fun Tags(listTags: List<CategoryModel>, idSelectTag : Int, onCheckTag : (Int)->Unit) {
    LazyRow(content = {
        items(listTags, key = { it.id }) {
            ItemTag(
                tag = it,
                colorSelect = TitleColor,
                colorUnSelect = TextColor,
                modifier = Modifier.padding(end = 35.dp),
                isSelect = it.id == idSelectTag
            ) {tag->
                onCheckTag(tag.id)
            }
        }
    })
}

@Composable
fun ItemTag(
    tag: CategoryModel,
    colorSelect: Color,
    colorUnSelect: Color,
    modifier: Modifier,
    isSelect: Boolean,
    onTap: (CategoryModel) -> Unit
) {
    with(tag) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.clickable { onTap(tag) })
        {
            Text(
                nameTag,
                color = if (isSelect) colorSelect else colorUnSelect,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp
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
fun Search(search: String,onClickFilter : ()->Unit, onTextChanged : (String)->Unit) {
    Row {
        CustomTextField(
            value = search,
            onTextChanged = onTextChanged,
            modifier = Modifier.weight(1f),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Sharp.Search,
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
                .clickable { onClickFilter() },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "button filter",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(20.dp)
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Header() {
    Row {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Hi Bunny!",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = TitleColor
            )
            Text(
                text = "Are you looking for pets?",
                fontSize = 15.sp,
                color = TextColor,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        GlideImage(
            model = "https://images.unsplash.com/photo-1618641986557-1ecd230959aa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8cHJvZmlsZXxlbnwwfHwwfHw%3D&w=1000&q=80",
            contentDescription = "user image",
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )
    }
}
