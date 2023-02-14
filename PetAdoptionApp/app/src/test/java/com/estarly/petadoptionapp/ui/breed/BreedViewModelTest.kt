package com.estarly.petadoptionapp.ui.breed

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.domain.pets.GetPetsUseCase
import com.estarly.petadoptionapp.ui.model.PetModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
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

internal class BreedViewModelTest {
    @RelaxedMockK
    private lateinit var getPetsUseCase: GetPetsUseCase
    private lateinit var breedViewModel: BreedViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        breedViewModel = BreedViewModel(getPetsUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `When getPetsUseCase is called, with the usecase response set the livedata variable`() = runTest{
        coEvery { getPetsUseCase(0) } returns BaseResultUseCase.Success(
            listOf(
                PetModel(1, "", "", "", 1, "", "", 12.0, false)
            )
        )
        breedViewModel.getPets(0)
        coVerify(exactly = 1) {getPetsUseCase(0)  }
        assert(breedViewModel.pets.value!!.isNotEmpty())

    }
}