package com.estarly.petadoptionapp.domain.pets

import android.util.Log
import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.PetsRepository
import com.estarly.petadoptionapp.domain.model.PetModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPetsUseCase @Inject constructor(private val petsRepository : PetsRepository){
    suspend operator fun invoke(idBreed : Int) : BaseResultUseCase<List<PetModel>>{
        return try {
            Log.i("GetPetsUseCase","$idBreed")
            when(val response = petsRepository.getPets(idBreed)){
                is BaseResultRepository.Error -> BaseResultUseCase.Error(response.exception)
                BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                is BaseResultRepository.Success -> BaseResultUseCase.Success(response.data)
            }
        }catch (e: Exception){
            BaseResultUseCase.Error(e)
        }
    }
}
