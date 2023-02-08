package com.estarly.petadoptionapp.domain

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.BreedRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


internal class GetBreedsUseCaseTest{
    @RelaxedMockK
    private lateinit var breedRepository : BreedRepository
    private lateinit var breedsUseCase: GetBreedsUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        breedsUseCase = GetBreedsUseCase(breedRepository)
    }
    @Test
    fun `when the repository return a null value`() = runBlocking{
        coEvery { breedRepository.getBreeds()  } returns BaseResultRepository.NullOrEmptyData
        val response = breedsUseCase()

        coVerify(exactly = 1) { breedRepository.getBreeds()  }
        assert(response == BaseResultUseCase.NullOrEmptyData){"Se esperaba que el repositorio devolviera un null"}
    }

}