package com.estarly.petadoptionapp.domain.promotion

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.PromotionRepository
import com.estarly.petadoptionapp.domain.promotion.model.PromotionResponse
import com.estarly.petadoptionapp.ui.model.PromotionModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPromotionUseCase @Inject constructor(private val promotionRepository: PromotionRepository) {
    suspend operator fun invoke(): BaseResultUseCase<PromotionModel> {
        return try {
            val response = promotionRepository.getPromotion()
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
        image      = this.code,
        percentage = this.percentage
    )
}