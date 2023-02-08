package com.estarly.petadoptionapp.data.repositories

import android.util.Log
import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.data.api.clients.PromotionClient
import com.estarly.petadoptionapp.data.api.response.PromotionResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PromotionRepository @Inject constructor(private val promotionClient: PromotionClient){
    private val TAG ="PromotionRepository"
    suspend fun  getPromotion() : BaseResultRepository<PromotionResponse>{
        return  try {
            val response = promotionClient.getPromotion()// get promotion
            Log.i(TAG,response.toString())
            if(response != null){
                BaseResultRepository.Success(
                    response
                )
            } else {
                BaseResultRepository.NullOrEmptyData
            }
        }catch (e:Exception){
            e.printStackTrace()
            BaseResultRepository.Error(e)
        }
    }
}