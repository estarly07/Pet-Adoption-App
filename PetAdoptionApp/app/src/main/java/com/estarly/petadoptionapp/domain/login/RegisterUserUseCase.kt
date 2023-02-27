package com.estarly.petadoptionapp.domain.login

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.LoginRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val preferencesUseCase: SetLoginPreferencesUseCase
){
    suspend operator fun invoke(name: String, email:String, pass:String) : BaseResultUseCase<Boolean>{
        return try {
            val response = loginRepository.createAccountByEmailAndPass(email,pass)
            when(response){
                is BaseResultRepository.Error -> BaseResultUseCase.Error(response.exception)
                BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                is BaseResultRepository.Success -> {
                    when(val response = loginRepository.createUser(response.data.user!!.uid,name,email)){
                        is BaseResultRepository.Error -> BaseResultUseCase.Error(response.exception)
                        BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                        is BaseResultRepository.Success -> {
                            preferencesUseCase.setIsLogin(true)
                            BaseResultUseCase.Success(true)
                        }
                    }
                }
            }
        }catch (e : Exception){
            BaseResultUseCase.Error(e)
        }
    }
}
