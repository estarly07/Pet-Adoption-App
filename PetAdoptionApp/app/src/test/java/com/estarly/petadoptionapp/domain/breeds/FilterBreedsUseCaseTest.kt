package com.estarly.petadoptionapp.domain.breeds

import com.estarly.petadoptionapp.ui.model.BreedModel
import org.junit.Test

internal class FilterBreedsUseCaseTest {
    private val filterBreedsUseCase = FilterBreedsUseCase()
    @Test
    fun `When filtering by quantity, it must return from highest to lowest`(){
        val listDisorder = listOf(
            BreedModel(breedName = "", amount = 23, image = "", idCategory = 0),
            BreedModel(breedName = "", amount = 13, image = "", idCategory = 0),
            BreedModel(breedName = "", amount = 32, image = "", idCategory = 0),
            BreedModel(breedName = "", amount = 12, image = "", idCategory = 0),
            BreedModel(breedName = "", amount = 25, image = "", idCategory = 0),
        )
        val response = filterBreedsUseCase("Cantidad",null, listDisorder)
        assert(response == listDisorder.sortedByDescending { it.amount }){
            "$response"
        }
    }
    @Test
    fun `When filtering by name, it must return in alphabetical order`(){
        val listDisorder = listOf(
            BreedModel(breedName = "a", amount = 23, image = "", idCategory = 0),
            BreedModel(breedName = "s", amount = 13, image = "", idCategory = 0),
            BreedModel(breedName = "v", amount = 32, image = "", idCategory = 0),
            BreedModel(breedName = "e", amount = 12, image = "", idCategory = 0),
            BreedModel(breedName = "r", amount = 25, image = "", idCategory = 0),
        )
        val response = filterBreedsUseCase("Nombre",null,listDisorder)
        assert(response == listDisorder.sortedBy { it.breedName })
    }
    @Test
    fun `When filtering by name and id category, it must return in alphabetical order`(){
        val listDisorder = listOf(
            BreedModel(breedName = "a", amount = 23, image = "", idCategory = 0),
            BreedModel(breedName = "s", amount = 13, image = "", idCategory = 1),
            BreedModel(breedName = "v", amount = 32, image = "", idCategory = 2),
            BreedModel(breedName = "e", amount = 12, image = "", idCategory = 1),
            BreedModel(breedName = "r", amount = 25, image = "", idCategory = 0),
        )
        val response = filterBreedsUseCase("Nombre",1,listDisorder)
        assert(response == listDisorder.sortedBy { it.breedName }.filter { it.idCategory == 1 })
    }
    @Test
    fun `When filtering and passing the idCategory which belongs to all then it should return the same list`(){
        val listDisorder = listOf(
            BreedModel(breedName = "a", amount = 23, image = "", idCategory = 0),
            BreedModel(breedName = "s", amount = 13, image = "", idCategory = 1),
            BreedModel(breedName = "v", amount = 32, image = "", idCategory = 2),
            BreedModel(breedName = "e", amount = 12, image = "", idCategory = 1),
            BreedModel(breedName = "r", amount = 25, image = "", idCategory = 0),
        )
        val response = filterBreedsUseCase(idCategory = -1, list = listDisorder, attribute = "")
        assert(response == listDisorder){
            "La respuesta al filtro deberia ser igual a la que se le pasa por parametro ya" +
                    " que se esta pidiendp que filtre para la categoria All "
        }
    }
}