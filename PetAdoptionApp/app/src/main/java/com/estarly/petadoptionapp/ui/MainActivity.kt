package com.estarly.petadoptionapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import com.estarly.petadoptionapp.ui.home.HomeScreen
import com.estarly.petadoptionapp.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel : HomeViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.onCreate()
        setContent {
            Scaffold {
                HomeScreen(homeViewModel)
            }
        }
    }

}


