package com.estarly.petadoptionapp.domain.login

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.LoginRepository
import com.estarly.petadoptionapp.domain.model.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository
){
    suspend operator fun invoke(uid : String) : BaseResultUseCase<UserModel>{
        return try {
            when(val response = loginRepository.getUserApi(uid)){
                is BaseResultRepository.Error -> BaseResultUseCase.Error(response.exception)
                BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                is BaseResultRepository.Success -> BaseResultUseCase.Success(response.data)
            }
        }catch (e : Exception){
            BaseResultUseCase.Error(e);
        }
    }
}