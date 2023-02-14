package com.estarly.petadoptionapp.domain.pets

import android.util.Log
import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.data.api.clients.PetClient
import com.estarly.petadoptionapp.data.repositories.PetsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetPetsUseCaseTest{
    @RelaxedMockK
    private lateinit var petClient : PetClient
    private lateinit var getPetsUseCase: GetPetsUseCase
    private lateinit var petsRepository : PetsRepository
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        petsRepository = PetsRepository(petClient)
        getPetsUseCase = GetPetsUseCase(petsRepository)
    }

    @Test
    fun `When calling petClient returns null, the repository should return a NullOrEmptyData`() = runBlocking{
        mockkStatic(Log::class)
        coEvery { Log.i(any(),any()) } returns  0
        coEvery { petClient.getPets(0) } returns null
        val response = petsRepository.getPets(0)
        assert(response is BaseResultRepository.NullOrEmptyData){
            "el response deberia ser NullOrEmptyData pero es : $response"
        }
    }

    @Test
    fun `When calling petClient returns empty list, the repository should return a NullOrEmptyData`() = runBlocking{
        mockkStatic(Log::class)
        coEvery { Log.i(any(),any()) } returns 0
        coEvery { petClient.getPets(0) } returns listOf()

        val response = petsRepository.getPets(0)
        assert(response is BaseResultRepository.NullOrEmptyData){
            "Deberia devolver que la lista esta vacia osea NullOrEmptyData"
        }
    }
}