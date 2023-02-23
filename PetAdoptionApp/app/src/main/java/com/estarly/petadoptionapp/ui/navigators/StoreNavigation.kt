package com.estarly.petadoptionapp.ui.navigators

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.estarly.petadoptionapp.ui.model.ProductModel
import com.estarly.petadoptionapp.ui.product.ProductScreen
import com.estarly.petadoptionapp.ui.product.ProductViewModel
import com.estarly.petadoptionapp.ui.store.StoreScreen
import com.estarly.petadoptionapp.ui.store.StoreViewModel

@Composable
fun ProductNavigation(productModel: ProductModel, productViewModel : ProductViewModel){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ProductNavigation.ScreenProduct.route
    ){
        composable(
            ProductNavigation.ScreenProduct.route
        ){
            ProductScreen(productModel,productViewModel)
        }
    }
}
private sealed class ProductNavigation(val route : String){
    object ScreenProduct : ProductNavigation("screenProduct")
}