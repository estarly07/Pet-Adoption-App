package com.estarly.petadoptionapp.domain.promotion

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.PromotionRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetPromotionUseCaseTest {
    @RelaxedMockK
    private lateinit var promotionRepository: PromotionRepository
    lateinit var getPromotionUseCase: GetPromotionUseCase
    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPromotionUseCase = GetPromotionUseCase(promotionRepository)
    }
    @Test
    fun `when the repository return a null or empty`() = runBlocking {
        // when getPromotion is called return the NullOrEmptyData
        coEvery { promotionRepository.getPromotion() } returns BaseResultRepository.NullOrEmptyData
        val response = getPromotionUseCase()
        //check if getPromotion is called once
        coVerify(exactly = 1) { promotionRepository.getPromotion() }

        assert(response == BaseResultUseCase.NullOrEmptyData, { "El resultado esperado era null o vacio" })
    }
}