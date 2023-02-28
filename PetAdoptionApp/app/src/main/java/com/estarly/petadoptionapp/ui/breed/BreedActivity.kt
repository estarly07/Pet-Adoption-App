package com.estarly.petadoptionapp.ui.breed

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import com.estarly.petadoptionapp.domain.model.BreedModel
import com.estarly.petadoptionapp.ui.navigators.BreedNavigation
import com.estarly.petadoptionapp.ui.pet.PetViewModel
import com.estarly.petadoptionapp.ui.theme.PetAdoptionAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedActivity : ComponentActivity() {
    private val breedViewModel: BreedViewModel by viewModels()
    private val petViewModel: PetViewModel by viewModels()
    private lateinit var breed : BreedModel
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        breed = intent.getSerializableExtra("breed") as BreedModel
        breedViewModel.getPets(breed.idBreed)
        setContent {
            PetAdoptionAppTheme(false) {
                Scaffold {
                    BreedNavigation(breed = breed, breedViewModel = breedViewModel,petViewModel = petViewModel)
                }
            }
        }
    }
}

