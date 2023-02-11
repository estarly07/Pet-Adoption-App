package com.estarly.petadoptionapp.domain.breeds

import com.estarly.petadoptionapp.ui.model.BreedModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilterBreedsUseCase @Inject constructor(){
    operator fun invoke(attribute:String?, idCategory : Int?, list : List<BreedModel>) : List<BreedModel>{
        idCategory?.let {
            if(idCategory == -1){
                return  list
            }
        }
        return when(attribute){
            "Nombre"-> {
                val newList =  list.sortedBy { it.breedName }
                idCategory?.let { id->
                   return newList.filter { it.idCategory == id }
                }
                newList
            }
            "Cantidad"-> {
               val newList = list.sortedByDescending {it.amount}
                idCategory?.let {
                    return newList.filter {item-> item.idCategory == it  }
                }
                newList
            }
            else -> {
                idCategory?.let { id->
                  return  list.filter { it.idCategory == id }
                }
                list
            }
        }
    }
}