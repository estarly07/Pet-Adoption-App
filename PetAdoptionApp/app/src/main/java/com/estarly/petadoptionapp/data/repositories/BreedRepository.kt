package com.estarly.petadoptionapp.data.repositories

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.data.api.clients.BreedClient
import com.estarly.petadoptionapp.data.api.response.BreedResponse
import com.estarly.petadoptionapp.ui.model.BreedModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedRepository @Inject constructor(private val breedClient : BreedClient){
    suspend fun getBreeds () : BaseResultRepository<List<BreedModel>>{
        return try {
            val response = breedClient.getBreeds()
            if(response == null || response.isEmpty()){
                BaseResultRepository.NullOrEmptyData
            }else{
                BaseResultRepository.Success(response.map { it.toData() })
            }
        }catch (e: Exception){
            BaseResultRepository.Error(e)
        }
    }
}
private fun BreedResponse.toData() : BreedModel = BreedModel(
    image     = this.image,
    breedName = this.breedName,
    amount    = this.amount
)