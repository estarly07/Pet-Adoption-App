package com.estarly.petadoptionapp.data.repositories

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.data.api.clients.PetClient
import com.estarly.petadoptionapp.data.api.response.PetResponse
import com.estarly.petadoptionapp.ui.model.PetModel
import javax.inject.Inject

class PetsRepository @Inject constructor(private val petClient : PetClient){
    suspend fun getPets(idBreed : Int) : BaseResultRepository<List<PetModel>>{
        return try {
            val response = petClient.getPets(idBreed)
            if(response== null || response.isEmpty()){
                BaseResultRepository.NullOrEmptyData
            }else{
                BaseResultRepository.Success(response.map { it.toData() } )
            }
        }catch (e:Exception){
            BaseResultRepository.Error(e)
        }
    }

}
fun PetResponse.toData() : PetModel = PetModel(
    idPet   = this.idPet,
    namePet = this.namePet,
    image   = this.image,
    about   = this.about,
    months  = this.months,
    address = this.address,
    sex     = this.sex,
    amount  = this.amount,
    adopted = this.adopted,
)
