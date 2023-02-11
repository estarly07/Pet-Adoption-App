package com.estarly.petadoptionapp.domain.categories

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.CategoriesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetCategoriesUseCaseTest{
    @RelaxedMockK
    private lateinit var categoriesRepository : CategoriesRepository
    private lateinit var getCategoriesUseCase : GetCategoriesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getCategoriesUseCase = GetCategoriesUseCase(categoriesRepository)
    }
    @Test
    fun `When the repository's getCategories method returns null the use case must return null`() = runBlocking{
        coEvery { categoriesRepository.getCategories() } returns BaseResultRepository.NullOrEmptyData
        val response = getCategoriesUseCase()
        assert(response == BaseResultUseCase.NullOrEmptyData)
    }
}
