package com.estarly.petadoptionapp.domain.breeds

import android.util.Log
import com.estarly.petadoptionapp.ui.model.BreedModel
import org.junit.Test

internal class FilterBreedsUseCaseTest {
    private val filterBreedsUseCase = FilterBreedsUseCase()
    @Test
    fun `When filtering by quantity, it must return from highest to lowest`(){
        val listDisorder = listOf(
            BreedModel(breedName = "", amount = 23, image = ""),
            BreedModel(breedName = "", amount = 13, image = ""),
            BreedModel(breedName = "", amount = 32, image = ""),
            BreedModel(breedName = "", amount = 12, image = ""),
            BreedModel(breedName = "", amount = 25, image = ""),
        )
        val response = filterBreedsUseCase("Cantidad","", listDisorder)
        assert(response == listDisorder.sortedByDescending { it.amount })
    }
    @Test
    fun `When filtering by name, it must return in alphabetical order`(){
        val listDisorder = listOf(
            BreedModel(breedName = "a", amount = 23, image = ""),
            BreedModel(breedName = "s", amount = 13, image = ""),
            BreedModel(breedName = "v", amount = 32, image = ""),
            BreedModel(breedName = "e", amount = 12, image = ""),
            BreedModel(breedName = "r", amount = 25, image = ""),
        )
        val response = filterBreedsUseCase("Nombre","",listDisorder)
        assert(response == listDisorder.sortedBy { it.breedName })
    }
}