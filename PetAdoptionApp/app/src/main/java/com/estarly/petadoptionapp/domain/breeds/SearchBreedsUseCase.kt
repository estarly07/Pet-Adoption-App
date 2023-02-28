package com.estarly.petadoptionapp.domain.breeds

import com.estarly.petadoptionapp.domain.model.BreedModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchBreedsUseCase @Inject constructor(){
    operator fun invoke(list: List<BreedModel>?, word : String) : List<BreedModel>{
        return list?.filter { it.breedName.uppercase().contains(word.uppercase()) || it.amount.toString().contains(word) } ?: listOf()
    }
}