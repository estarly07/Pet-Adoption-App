package com.estarly.petadoptionapp.domain.login

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.database.dao.UserDao
import com.estarly.petadoptionapp.data.preferences.Preferences
import com.estarly.petadoptionapp.data.repositories.LoginRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginByEmailAndPassUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val setLoginPreferencesUseCase: SetLoginPreferencesUseCase,
    private val getUserUseCase: GetUserUseCase
) {
    suspend operator fun invoke(email : String, pass : String) : BaseResultUseCase<Boolean> {
        return try {
            when(val response = loginRepository.loginByEmailAndPass(email, pass)){
                is BaseResultRepository.Success -> {
                    when(val responseUser = getUserUseCase(response.data.user!!.uid)){
                        is BaseResultUseCase.Error -> TODO()
                        BaseResultUseCase.NoInternetConnection -> TODO()
                        BaseResultUseCase.NullOrEmptyData -> TODO()
                        is BaseResultUseCase.Success -> {
                            setLoginPreferencesUseCase.setIsLogin(true)
                            loginRepository.insertUser(responseUser.data)
                            BaseResultUseCase.Success(true)
                        }
                    }
                }
                is BaseResultRepository.Error -> BaseResultUseCase.Error(response.exception)
                BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
            }
        }catch (e : Exception){
            BaseResultUseCase.Error(e)
        }
    }
}