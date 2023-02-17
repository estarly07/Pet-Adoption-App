package com.estarly.petadoptionapp.ui.home

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.composables.*
import com.estarly.petadoptionapp.ui.dialog.filter.CustomDialogFilter
import com.estarly.petadoptionapp.ui.model.BreedModel
import com.estarly.petadoptionapp.ui.model.PromotionModel
import com.estarly.petadoptionapp.ui.model.CategoryModel
import com.estarly.petadoptionapp.ui.navigator.Route
import com.estarly.petadoptionapp.ui.theme.*
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navigationController: NavHostController) {
    val showProgressPromotion: Boolean by homeViewModel.showProgressPromotion.observeAsState(initial = true)
    val showProgressBreeds: Boolean by homeViewModel.showProgressBreeds.observeAsState(initial = true)
    val showProgressTags: Boolean by homeViewModel.showProgressCategories.observeAsState(initial = true)
    val promotion: PromotionModel? by homeViewModel.promotion.observeAsState(initial = null)
    val breeds: List<BreedModel>? by homeViewModel.breeds.observeAsState(initial = null)
    val search: String by homeViewModel.search.observeAsState(initial = "")
    val isSearching: Boolean by homeViewModel.isSearching.observeAsState(initial = true)
    val idSelectTag: Int by homeViewModel.idSelectTag.observeAsState(initial = 0)
    val tags: List<CategoryModel> by homeViewModel.tags.observeAsState(initial = listOf())
    val showDialogFilter: Boolean by homeViewModel.showDialogFilter.observeAsState(initial = false)
    Scaffold{
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = MarginHorizontalScreen)
        ) {
            CustomSpaceHeight(height = 25.dp)
            Header()
            CustomSpaceHeight(height = 25.dp)
            Search(
                search,
                onClickFilter = { homeViewModel.showDialogFilter() }) { homeViewModel.searchBread(it) }
            CustomSpaceHeight(height = 28.dp)
            if (isSearching) {
                Tags(
                    tags,
                    idSelectTag,
                    wait = showProgressTags
                ) { id -> homeViewModel.changeSelectTag(id) }
                CustomSpaceHeight(height = 20.dp)
                PromotionCard(promotion, showProgressPromotion)
                CustomSpaceHeight(height = 20.dp)
                TitleAndViewAll()
                CustomSpaceHeight(height = 17.dp)
            }
            Pets(breeds, wait = showProgressBreeds) { breed ->
                navigationController.navigate(
                    Route.ScreenBreed.createRoute(
                        breed.idBreed,
                        breed.breedName,
                        breed.image
                    )
                )
            }
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
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Pets(breeds: List<BreedModel>?, wait: Boolean, onClick: (BreedModel) -> Unit) {
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

@Composable
fun TitleAndViewAll() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Top Breeds",
            color = MaterialTheme.colors.onPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "View all",
            color = MaterialTheme.colors.onSecondary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PromotionCard(promotion: PromotionModel?, showProgressPromotion: Boolean) {
    Log.i("PromotionCard", "${!showProgressPromotion} && ${promotion == null}")
    CustomCard(wait = showProgressPromotion, height = 140.dp) {
        promotion?.let {
            with(promotion) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary,
                            fontSize = 17.sp
                        )
                        CustomSpaceHeight(height = 5.dp)
                        Text(
                            text = "Upto $percentage% off",
                            color = MaterialTheme.colors.primary,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        CustomSpaceHeight(height = 5.dp)
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onSecondary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Use code ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.primary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(code.uppercase(Locale.getDefault()))
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onSecondary,
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
fun Tags(
    listTags: List<CategoryModel>,
    idSelectTag: Int,
    wait: Boolean,
    onCheckTag: (Int) -> Unit
) {
    if (wait) {
        LazyRow {
            items(4) {
                CustomShimmerRectangleWait(
                    modifier = Modifier
                        .padding(end = 35.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .height(15.dp)
                        .width(40.dp)
                )
            }
        }
    } else {
        LazyRow(content = {
            items(listTags, key = { it.id }) {
                ItemTag(
                    tag = it,
                    colorSelect = MaterialTheme.colors.onPrimary,
                    colorUnSelect = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.padding(end = 35.dp),
                    isSelect = it.id == idSelectTag,
                ) { tag ->
                    onCheckTag(tag.id)
                }
            }
        })
    }
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
fun Search(search: String, onClickFilter: () -> Unit, onTextChanged: (String) -> Unit) {
    Row {
        CustomTextField(
            value = search,
            onTextChanged = onTextChanged,
            modifier = Modifier.weight(1f),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Sharp.Search,
                    contentDescription = "Icon search",
                    tint = MaterialTheme.colors.onSecondary

                )
            },
            textColor = MaterialTheme.colors.onSecondary,
            backgroundColor = MaterialTheme.colors.secondary,
            placerHolder = "Search"
        )
        Spacer(modifier = Modifier.width(10.dp))
        CustomButton(
            modifier = Modifier.size(50.dp),
            composable = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "button filter",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(20.dp)
                )
            }
        ) {
            onClickFilter()
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
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = "Are you looking for pets?",
                fontSize = 15.sp,
                color = MaterialTheme.colors.onSecondary,
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
