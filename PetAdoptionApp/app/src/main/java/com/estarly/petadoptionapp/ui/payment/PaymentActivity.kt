package com.estarly.petadoptionapp.ui.payment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.estarly.petadoptionapp.ui.theme.PetAdoptionAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class PaymentActivity : ComponentActivity() {
    private val selectPaymentViewModel :SelectPaymentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectPaymentViewModel.getListPayments()
        setContent {
            PetAdoptionAppTheme(darkTheme = false) {
                PaymentScreen(selectPaymentViewModel)
            }
        }
    }
}
