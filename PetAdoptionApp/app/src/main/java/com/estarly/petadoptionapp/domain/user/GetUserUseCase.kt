package com.estarly.petadoptionapp.domain.user

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.UserRepository
import com.estarly.petadoptionapp.domain.model.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke() : BaseResultUseCase<UserModel>{
        return try {
            val response = userRepository.getUser()
            when(response){
                is BaseResultRepository.Error -> TODO()
                BaseResultRepository.NullOrEmptyData -> TODO()
                is BaseResultRepository.Success -> BaseResultUseCase.Success(response.data)
            }
        }catch (e:Exception){
            BaseResultUseCase.Error(e)
        }
    }
}