package com.estarly.petadoptionapp.domain.breeds

import android.util.Log
import com.estarly.petadoptionapp.ui.model.BreedModel
import io.mockk.every
import org.junit.Test

internal class SearchBreedsUseCaseTest{
    private val searchBreedsUseCase = SearchBreedsUseCase()

    @Test
    fun `when the use case search has an empty word, it must return the normal list with all the elements`(){
        val list = listOf(
            BreedModel("h",0,""),
            BreedModel("",0,""),
            BreedModel("",0,""),
        )
        val response = searchBreedsUseCase(list,"")
        assert(response.size == list.size)
    }
    @Test
    fun `when the search use case finds breeds Names that contain the word passed by parameter it returns a filtered list`(){
        val list = listOf(
            BreedModel("One",0,""),
            BreedModel("Two",0,""),
            BreedModel("Three",0,""),
        )
        val response = searchBreedsUseCase(list,"t")
        assert(response.size == 2)
    }
    @Test
    fun `when the search use case finds amounts that contain the word passed by parameter it returns a filtered list`(){
        val list = listOf(
            BreedModel("One",0,""),
            BreedModel("Two",1,""),
            BreedModel("Three",2,""),
        )
        val response = searchBreedsUseCase(list,"1")
        assert(response.size == 1)
    }
}