package com.estarly.petadoptionapp.ui.product

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.estarly.petadoptionapp.domain.model.ProductModel
import com.estarly.petadoptionapp.ui.navigators.ProductNavigation
import com.estarly.petadoptionapp.ui.theme.PetAdoptionAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductActivity : ComponentActivity() {
    private val productViewModel : ProductViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val product = intent.getSerializableExtra("product") as ProductModel
        setContent{
            PetAdoptionAppTheme(darkTheme = false) {
                  ProductNavigation(product,productViewModel)
            }
        }
    }
}
