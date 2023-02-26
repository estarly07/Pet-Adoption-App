package com.estarly.petadoptionapp.domain.login

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.preferences.Preferences
import com.estarly.petadoptionapp.data.repositories.LoginRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginByEmailAndPassUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val setLoginPreferencesUseCase: SetLoginPreferencesUseCase
) {
    suspend operator fun invoke(email : String, pass : String) : BaseResultUseCase<Boolean> {
        return try {
            val response = loginRepository.loginByEmailAndPass(email, pass)
            when(response){
                is BaseResultRepository.Success -> {
                    setLoginPreferencesUseCase.setIsLogin(true)
                    BaseResultUseCase.Success(response.data)
                }
                is BaseResultRepository.Error -> BaseResultUseCase.Error(response.exception)
                BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
            }
        }catch (e : Exception){
            BaseResultUseCase.Error(e)
        }
    }
}