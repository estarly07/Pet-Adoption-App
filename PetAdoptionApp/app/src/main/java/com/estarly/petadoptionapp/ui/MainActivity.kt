package com.estarly.petadoptionapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.rememberNavController
import com.estarly.petadoptionapp.ui.home.HomeViewModel
import com.estarly.petadoptionapp.ui.home.navigation.CustomBottomBar
import com.estarly.petadoptionapp.ui.home.navigation.NavigationViewModel
import com.estarly.petadoptionapp.ui.navigators.MainAppNavigation
import com.estarly.petadoptionapp.ui.theme.PetAdoptionAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val navigationViewModel : NavigationViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.onCreate()
        setContent {
            val idSelectButton by navigationViewModel.idSelectNavigationBar.observeAsState(initial = 0)
            val navController = rememberNavController()
            PetAdoptionAppTheme(false) {
                Scaffold(
                    bottomBar = { CustomBottomBar(idSelectButton,navigationViewModel.listButtonsNavigation){
                        navigationViewModel.changeScreen(it,navController)
                    } }
                ) {
                    MainAppNavigation(homeViewModel,navController)
                }
            }
        }
    }
}


