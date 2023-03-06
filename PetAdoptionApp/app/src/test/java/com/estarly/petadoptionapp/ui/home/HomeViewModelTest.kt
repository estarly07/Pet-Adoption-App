package com.estarly.petadoptionapp.ui.home

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.breeds.FilterBreedsUseCase
import com.estarly.petadoptionapp.domain.breeds.GetBreedsUseCase
import com.estarly.petadoptionapp.domain.breeds.SearchBreedsUseCase
import com.estarly.petadoptionapp.domain.categories.GetCategoriesUseCase
import com.estarly.petadoptionapp.domain.promotion.GetPromotionUseCase
import com.estarly.petadoptionapp.domain.model.CategoryModel
import com.estarly.petadoptionapp.domain.model.PromotionModel
import com.estarly.petadoptionapp.domain.user.GetUserUseCase
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
    @RelaxedMockK private lateinit var getBreedsUseCase: GetBreedsUseCase
    @RelaxedMockK private lateinit var searchBreedsUseCase: SearchBreedsUseCase
    @RelaxedMockK private lateinit var filterBreedsUseCase: FilterBreedsUseCase
    @RelaxedMockK private lateinit var getCategoriesUseCase: GetCategoriesUseCase
    @RelaxedMockK private lateinit var getUserUseCase: GetUserUseCase
    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(
            getPromotionUseCase,
            getBreedsUseCase,
            searchBreedsUseCase,
            filterBreedsUseCase,
            getCategoriesUseCase,
            getUserUseCase
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }
    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }
    @Test
    fun `When the viewModel is created the first time, return null or empty data`() = runTest{
        mockkStatic(Log::class)
        coEvery { Log.i(any(),any()) } returns 0
        coEvery { getPromotionUseCase() } returns BaseResultUseCase.NullOrEmptyData
        homeViewModel.onCreate()
        coVerify(exactly = 1) { getPromotionUseCase() }

        assert(homeViewModel.promotion.value == null) { "El resultado no fue null" }
    }
    @Test
    fun `when the useCase return a success set promotion` () = runTest{
        val expectedValue = PromotionModel(id =0, title = "test", code = "TEST", image = "", percentage = "50")
        mockkStatic(Log::class)
        coEvery { Log.i(any(),any()) } returns 0
        coEvery { getPromotionUseCase() } returns BaseResultUseCase.Success(expectedValue)
        homeViewModel.onCreate()
        coVerify(exactly = 1) { getPromotionUseCase() }

        assert(homeViewModel.promotion.value == expectedValue){"El resultado es distinto al esperado"}
    }

    @Test
    fun `when the useCase in the fun getBreeds return null`()  = runTest{
        mockkStatic(Log::class)
        coEvery { Log.i(any(),any()) } returns 0
        coEvery { getBreedsUseCase() } returns BaseResultUseCase.NullOrEmptyData
        homeViewModel.onCreate()
        assert(homeViewModel.breeds.value == null){"El caso de uso no devolvio null"}
    }

    @Test
    fun `When the list is null and is filtered by name or quantity, it must return an empty list`(){
        homeViewModel.filter("Nombre",null)
        assert(homeViewModel.breeds.value!!.isEmpty())
    }

    @Test
    fun `When getCategoriesUseCase is called it returns a list the tag selected by default must be the category named All`() = runTest{
        mockkStatic(Log::class)
        coEvery { Log.i(any(),any()) } returns 0
        coEvery { getCategoriesUseCase() } returns BaseResultUseCase.Success(
            listOf(
                CategoryModel(0,"d"),
                CategoryModel(1,"s"),
                CategoryModel(-1,"All"),
                CategoryModel(3,"a"),
            )
        )
        homeViewModel.onCreate()
        assert(homeViewModel.idSelectTag.value == -1)
    }
    @Test
    fun `When getCategoriesUseCase is called it returns null the tag selected by default must be null`() = runTest{
        mockkStatic(Log::class)
        coEvery { Log.i(any(),any()) } returns 0
        coEvery { getCategoriesUseCase() } returns BaseResultUseCase.NullOrEmptyData
        homeViewModel.onCreate()
        assert(homeViewModel.idSelectTag.value == null)
    }
}