package com.estarly.petadoptionapp.ui.cart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.estarly.petadoptionapp.ui.theme.PetAdoptionAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : ComponentActivity() {
    private val cartViewModel : CartViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartViewModel.getProducts()
        setContent {
            PetAdoptionAppTheme(darkTheme = false) {
                CartScreen(cartViewModel)
            }
        }
    }
}
