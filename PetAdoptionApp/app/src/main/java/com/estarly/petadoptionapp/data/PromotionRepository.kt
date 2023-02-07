package com.estarly.petadoptionapp.data

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.domain.promotion.model.PromotionResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PromotionRepository @Inject constructor(){
    suspend fun  getPromotion() : BaseResultRepository<PromotionResponse>{
        return  try {
            BaseResultRepository.Success(
                PromotionResponse(
                    id = 0,
                    title = "Promotion hecha en el repositorio",
                    code = "REPO:)",
                    image = "",
                    percentage = "100"
                )
            )
        }catch (e:Exception){
            BaseResultRepository.Error(e)
        }
    }
}