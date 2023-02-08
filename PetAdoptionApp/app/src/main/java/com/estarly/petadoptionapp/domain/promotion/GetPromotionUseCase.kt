package com.estarly.petadoptionapp.domain.promotion

import android.util.Log
import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.PromotionRepository
import com.estarly.petadoptionapp.data.api.response.PromotionResponse
import com.estarly.petadoptionapp.ui.model.PromotionModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPromotionUseCase @Inject constructor(private val promotionRepository: PromotionRepository) {
    private val TAG ="GetPromotionUseCase"
    suspend operator fun invoke(): BaseResultUseCase<PromotionModel> {
        return try {
            val response = promotionRepository.getPromotion()//get promotion
            Log.i(TAG,response.toString())
            when (response) {
                BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                is BaseResultRepository.Success      -> BaseResultUseCase.Success(response.data.toData())
                is BaseResultRepository.Error        -> BaseResultUseCase.Error(response.exception)
            }
        } catch (e: Exception) {
            BaseResultUseCase.Error(exception = e)
        }

    }
}
fun PromotionResponse.toData() : PromotionModel{
    return  PromotionModel(
        id         = this.id,
        title      = this.title,
        code       = this.code,
        image      = this.image,
        percentage = this.percentage
    )
}