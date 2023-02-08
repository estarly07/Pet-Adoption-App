package com.estarly.petadoptionapp.data.repositories

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.data.api.response.BreedResponse
import com.estarly.petadoptionapp.ui.model.BreedModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedRepository @Inject constructor(){
    fun getBreeds () : BaseResultRepository<List<BreedModel>>{
        return BaseResultRepository.Success(
            listOf(
                BreedResponse(
                    breedName = "American Curl",
                    amount = 120,
                    image = "https://www.wikichat.fr/wp-content/uploads/sites/2/AMERICAN_CURL1.png"
                ),
                BreedResponse(
                    breedName = "British Shorthair",
                    amount = 20,
                    image = "https://i.pinimg.com/originals/66/1a/13/661a136baa0e6631652ab6ae04a69bdf.png"
                ),
                BreedResponse(
                    breedName = "English Cocker",
                    amount = 10,
                    image = "https://static.wixstatic.com/media/d8fd22_470f60f993a74654ab21d9b8a3f3be5f~mv2.png/v1/fill/w_535,h_573,al_c/d8fd22_470f60f993a74654ab21d9b8a3f3be5f~mv2.png"
                ),
            ).map {
                it.toData()
            }
        )
    }
}
private fun BreedResponse.toData() : BreedModel = BreedModel(
    image     = this.image,
    breedName = this.breedName,
    amount    = this.amount
)