package com.estarly.petadoptionapp.ui.cart

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.estarly.petadoptionapp.R
import com.estarly.petadoptionapp.ui.composables.CustomAddOrDismiss
import com.estarly.petadoptionapp.ui.composables.CustomButton
import com.estarly.petadoptionapp.ui.composables.CustomSpaceHeight
import com.estarly.petadoptionapp.ui.dialog.alert.CustomAlertDialog
import com.estarly.petadoptionapp.ui.model.ProductCartModel
import com.estarly.petadoptionapp.ui.theme.MarginHorizontalScreen
import com.estarly.petadoptionapp.utils.format

@Composable
fun CartScreen(cartViewModel: CartViewModel) {
    val context = LocalContext.current
    val listProducts by cartViewModel.listProducts.observeAsState(initial = listOf())
    val totalPrice by cartViewModel.totalPrice.observeAsState(initial = 0.0)
    val showAlertDialog by cartViewModel.showAlertDialog.observeAsState(initial = false)
    Column(
        modifier = Modifier.padding(horizontal = MarginHorizontalScreen)
    ) {
        CustomSpaceHeight(height = 25.dp)
        Header(
            onBack = {(context as Activity).onBackPressed()},
            onDelete = { cartViewModel.showDeleteCartAlertDialog(true) }
        )
        CustomSpaceHeight(height = 25.dp)
        Products(modifier = Modifier.weight(1f),listProducts,cartViewModel)
        CustomSpaceHeight(height = 20.dp)
        Footer(totalPrice)
        //show dialog delete cart
        CustomAlertDialog(
            title = "Alert",
            message = "Do you want to delete the cart?",
            show = showAlertDialog,
            onDismiss = {cartViewModel.showDeleteCartAlertDialog(false)},
            onConfirm = {
                cartViewModel.deleteCart()
            }
        )
    }
}

@Composable
fun Footer(totalPrice: Double) {
    Column( modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Max)
        .padding(
            bottom = MarginHorizontalScreen
        ),){
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.onSecondary)
        )
        CustomSpaceHeight(height = 15.dp)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Total", color = MaterialTheme.colors.onSecondary, fontSize = 15.sp)
            Text(
                text = "$${totalPrice.format()}",
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
        CustomSpaceHeight(height = 20.dp)
        CustomButton(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            composable = {
                Text(
                    text = "Proceed to Checkout",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 5.dp)
                )
        },
            onClick = {},
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun Products(
    modifier: Modifier,
    listProducts: List<ProductCartModel>,
    cartViewModel: CartViewModel,
    ) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        itemsIndexed(listProducts) {i,product->
            ItemCart(
                product,
                onAdd = {
                    cartViewModel.addCant(i,product)
                },
                onSubtract = {
                    cartViewModel.addSubtract(i,product)
                }
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemCart(
    product: ProductCartModel,
    onAdd:()->Unit,
    onSubtract:()->Unit,
) {
    with(product) {
        Card(
            elevation = 5.dp,
            backgroundColor = Color.White,
            shape = RoundedCornerShape(25.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlideImage(
                    model = productModel.imageProduct,
                    contentDescription = "",
                    modifier = Modifier.size(100.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = productModel.nameProduct,
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    CustomAddOrDismiss(
                        product.cant,
                        { onAdd()},
                        { onSubtract() }
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_like),
                        contentDescription = ""
                    )
                    Text(
                        text = "$${productModel.price.format()}",
                        color = MaterialTheme.colors.primary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                }
            }
        }
    }
}

@Composable
fun Header(
    onBack : ()->Unit,
    onDelete : ()->Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = "icon back",
            modifier = Modifier.clickable {onBack()})
        Text(
            text = "My Order",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = MaterialTheme.colors.onPrimary
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = "icon back",
            modifier = Modifier.clickable {onDelete()})
    }
}
