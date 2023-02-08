package com.estarly.petadoptionapp.domain.breeds

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.BreedRepository
import com.estarly.petadoptionapp.ui.model.BreedModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBreedsUseCase @Inject constructor(private val breedRepository: BreedRepository) {
    suspend operator fun invoke(): BaseResultUseCase<List<BreedModel>> {
        return try {
            when (val response = breedRepository.getBreeds()) {
                BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                is BaseResultRepository.Error -> BaseResultUseCase.Error(response.exception)
                is BaseResultRepository.Success -> BaseResultUseCase.Success(response.data)
            }
        } catch (e: Exception) {
            BaseResultUseCase.Error(e)
        }

    }

}
