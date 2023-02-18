package com.estarly.petadoptionapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.estarly.petadoptionapp.ui.breed.BreedViewModel
import com.estarly.petadoptionapp.ui.home.HomeViewModel
import com.estarly.petadoptionapp.ui.home.navigation.CustomBottomBar
import com.estarly.petadoptionapp.ui.home.navigation.NavigationViewModel
import com.estarly.petadoptionapp.ui.navigator.AppNavigation
import com.estarly.petadoptionapp.ui.pet.PetViewModel
import com.estarly.petadoptionapp.ui.theme.PetAdoptionAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val breedViewModel: BreedViewModel by viewModels()
    private val petViewModel: PetViewModel by viewModels()
    private val navigationViewModel : NavigationViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.onCreate()
        setContent {
            val idSelectButton by navigationViewModel.idSelectNavigationBar.observeAsState(initial = 0)
            PetAdoptionAppTheme(false) {
                Scaffold(
                    bottomBar = { CustomBottomBar(idSelectButton,navigationViewModel.listButtonsNavigation){ navigationViewModel.changeScreen(it)} }
                ) {
                    AppNavigation(homeViewModel, breedViewModel, petViewModel)
                }
            }
        }
    }

}


