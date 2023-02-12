package com.estarly.petadoptionapp.domain.breeds

import com.estarly.petadoptionapp.ui.model.BreedModel
import org.junit.Test

internal class SearchBreedsUseCaseTest{
    private val searchBreedsUseCase = SearchBreedsUseCase()

    @Test
    fun `when the use case search has an empty word, it must return the normal list with all the elements`(){
        val list = listOf(
            BreedModel(0, "h", 0, "", idCategory = 0),
            BreedModel(0, "", 0, "", idCategory = 0),
            BreedModel(0, "", 0, "", idCategory = 0),
        )
        val response = searchBreedsUseCase(list,"")
        assert(response.size == list.size)
    }
    @Test
    fun `when the search use case finds breeds Names that contain the word passed by parameter it returns a filtered list`(){
        val list = listOf(
            BreedModel(0, "One", 0, "", idCategory = 0),
            BreedModel(0, "Two", 0, "", idCategory = 0),
            BreedModel(0, "Three", 0, "", idCategory = 0),
        )
        val response = searchBreedsUseCase(list,"t")
        assert(response.size == 2)
    }
    @Test
    fun `when the search use case finds amounts that contain the word passed by parameter it returns a filtered list`(){
        val list = listOf(
            BreedModel(0, "One", 0, "", idCategory = 0),
            BreedModel(0, "Two", 1, "", idCategory = 0),
            BreedModel(0, "Three", 2, "", idCategory = 0),
        )
        val response = searchBreedsUseCase(list,"1")
        assert(response.size == 1)
    }
}