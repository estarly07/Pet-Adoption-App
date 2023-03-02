package com.estarly.petadoptionapp.ui.product

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.runtime.*
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.*
import com.estarly.petadoptionapp.ui.cart.CartActivity
import com.estarly.petadoptionapp.ui.composables.CustomAddOrDismiss
import com.estarly.petadoptionapp.ui.composables.CustomButton
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.composables.CustomSpaceWidth
import com.estarly.petadoptionapp.domain.model.ProductModel
import com.estarly.petadoptionapp.ui.theme.MarginHorizontalScreen
import com.estarly.petadoptionapp.utils.format

@Composable
fun ProductScreen(productModel: ProductModel, productViewModel : ProductViewModel) {
    val localContext = LocalContext.current
    val cant by productViewModel.cant.observeAsState(initial=1)
    val imageSelect by productViewModel.imageSelect.observeAsState(initial = productModel.imageProduct)
    val showMoreAbout by productViewModel.showMoreAbout.observeAsState(initial = false)
    with(productModel) {
        Column(
            modifier = Modifier.padding(
                start = MarginHorizontalScreen,
                end = MarginHorizontalScreen,
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                CustomSpaceHeight(height = 25.dp)
                Header(nameTypeProduct,
                    onBack = {
                        (localContext as Activity).onBackPressed()
                    },
                )
                Images(productModel.images,imageSelect){
                    productViewModel.changeSelectImage(it)
                }
                About(nameProduct, aboutProduct,showMoreAbout){
                    productViewModel.showMoreAbout()
                }
                CustomSpaceHeight(height = 20.dp)
                PriceAndAdd(
                    amount = price,
                    onAdd = {
                        productViewModel.addCant(max = productModel.cant)
                    },
                    onSubtract = {
                        productViewModel.subtractCant()
                    },
                    cant = cant
                )
                CustomSpaceHeight(height = 20.dp)
                AddCart{
                    productViewModel.addProductCart(productModel)
//                    localContext.startActivity(Intent(localContext,CartActivity::class.java))
                }
                CustomSpaceHeight(height = 10.dp)
            }
        }
    }
}

@Composable
fun PriceAndAdd(
    amount: Double,
    cant : Int,
    onAdd : () ->Unit,
    onSubtract : () ->Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            CustomSlideUp(delay = 500){
                Text(
                    text = "from",
                    color = MaterialTheme.colors.onSecondary,
                    fontSize = 15.sp,
                )
            }
            CustomSlideUp(delay = 550){
                Text(
                    text = "$${amount.format()}",
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                )
            }
        }
        CustomSlideUp(delay = 600){
            CustomAddOrDismiss(
                cant,
                onAdd,
                onSubtract
            )
        }
    }
}

@Composable
fun About(title: String, about: String,showMoreAbout : Boolean, onShow:()->Unit) {
    val brush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colors.primary,
            MaterialTheme.colors.primaryVariant
        )
    )
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomSlideUp(
                delay = 300
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            }
            CustomSlideLeft(350) {
                FloatingActionButton(
                    onClick = { },
                    backgroundColor = MaterialTheme.colors.secondary,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_love),
                        contentDescription = "icon like",
                        modifier = Modifier
                            .size(30.dp)
                            .graphicsLayer(alpha = 0.99f)
                            .drawWithCache {
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(
                                        brush = brush,
                                        blendMode = BlendMode.SrcAtop
                                    )
                                }
                            },
                    )
                }
            }
        }
        CustomSpaceHeight(height = 10.dp)
        CustomSlideUp(
            delay = 350
        ){
            CustomAnimateExpandBounce {
                Text(
                    text = about,
                    maxLines = if (!showMoreAbout) 3 else Int.MAX_VALUE,
                    overflow = if (!showMoreAbout) TextOverflow.Ellipsis else TextOverflow.Visible,
                    color = MaterialTheme.colors.onSecondary,
                    fontSize = 15.sp,
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
                modifier = Modifier.clickable { onShow() }
            )
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Images(images: List<String>, imageSelect: String, onChangeImage : (String) ->Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomFadeIn(duration = 500, delay = 250, modifier = Modifier
            .weight(1f)){
            GlideImage(
                model = imageSelect,
                contentDescription = "",
                modifier = Modifier
                    .height(400.dp)
            )
        }
        CustomSpaceWidth(width = 10.dp)
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.height(400.dp)
        ) {
            for ((index,image) in images.withIndex()) {
                CustomFadeIn(
                    duration = 500,
                    delay = (250 + (index*70)),
                    modifier = Modifier.padding(bottom = if (index != images.size - 1) 10.dp else 0.dp)
                ){
                    Card(
                        elevation = 0.dp,
                        backgroundColor = if (imageSelect == image) MaterialTheme.colors.onSecondary.copy(
                            alpha = 0.2f
                        ) else Color.Transparent,
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .clickable { onChangeImage(image) }
                    ) {
                        ConstraintLayout(modifier = Modifier.padding( 5.dp)) {
                            val (icon, card) = createRefs()
                            if (imageSelect == image){
                                Icon(
                                    imageVector = Icons.Sharp.CheckCircle,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(15.dp)
                                        .constrainAs(icon) {
                                            top.linkTo(parent.top)
                                            end.linkTo(parent.end, margin = 5.dp)
                                        }
                                )
                            }
                            GlideImage(
                                model = image,
                                modifier = Modifier
                                    .size(60.dp)
                                    .constrainAs(card) {
                                        top.linkTo(icon.bottom, margin = 2.dp)
                                    },
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Header(nameTypeProduct: String,onBack : () ->Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        CustomSlideRight(
            delay = 100
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "icon back",
                modifier = Modifier.clickable {onBack()})
        }
        CustomSlideDown(
            delay = 150
        ) {
            Text(
                text = nameTypeProduct,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
            )
        }
        Box { }
    }
}

@Composable
fun AddCart( onCart : () -> Unit) {
    CustomSlideUp(delay = 650){
        Row{
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Red))
            CustomButton(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .weight(1f),
                composable = {
                    Text(
                        text = "Add to cart",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 5.dp)
                    )
                },
                onClick = {onCart()},
            )
        }
    }
}
