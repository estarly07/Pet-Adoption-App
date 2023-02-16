package com.estarly.petadoptionapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import com.estarly.petadoptionapp.ui.breed.BreedViewModel
import com.estarly.petadoptionapp.ui.home.HomeViewModel
import com.estarly.petadoptionapp.ui.navigator.AppNavigation
import com.estarly.petadoptionapp.ui.pet.PetViewModel
import com.estarly.petadoptionapp.ui.theme.PetAdoptionAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel  : HomeViewModel  by viewModels()
    private val breedViewModel : BreedViewModel by viewModels()
    private val petViewModel   : PetViewModel   by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.onCreate()
        setContent {
            PetAdoptionAppTheme(false) {
                Scaffold {
                 AppNavigation(homeViewModel,breedViewModel,petViewModel)
                }
            }
        }
    }

}


