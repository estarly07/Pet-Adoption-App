package com.estarly.petadoptionapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.promotion.GetPromotionUseCase
import com.estarly.petadoptionapp.ui.model.PromotionModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class HomeViewModelTest{
    @RelaxedMockK
    private lateinit var getPromotionUseCase : GetPromotionUseCase
    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(getPromotionUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }
    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }
    @Test
    fun `When the viewModel is created the first time, return null or empty data`() = runTest{
        coEvery { getPromotionUseCase() } returns BaseResultUseCase.NullOrEmptyData
        homeViewModel.onCreate()
        coVerify(exactly = 1) { getPromotionUseCase() }

        assert(homeViewModel.promotion.value == null) { "El resultado no fue null" }
    }
    @Test
    fun `when the useCase return a success set promotion` () = runTest{
        val expectedValue = PromotionModel(id =0, title = "test", code = "TEST", image = "", percentage = "50")
        coEvery { getPromotionUseCase() } returns BaseResultUseCase.Success(expectedValue)
        homeViewModel.onCreate()
        coVerify(exactly = 1) { getPromotionUseCase() }

        assert(homeViewModel.promotion.value == expectedValue){"El resultado es distinto al esperado"}
    }

}