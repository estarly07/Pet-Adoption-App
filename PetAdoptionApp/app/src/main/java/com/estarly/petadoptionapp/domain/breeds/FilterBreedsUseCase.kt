package com.estarly.petadoptionapp.domain.breeds

import com.estarly.petadoptionapp.ui.model.BreedModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilterBreedsUseCase @Inject constructor(){
    operator fun invoke(attribute:String,category : String, list : List<BreedModel>) : List<BreedModel>{
        return when(attribute){
            "Nombre"->  list.sortedBy {it.breedName}
            "Cantidad"-> list.sortedByDescending {it.amount}
            else -> list
        }
    }
}