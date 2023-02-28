package com.estarly.petadoptionapp.domain.login

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.LoginRepository
import com.estarly.petadoptionapp.domain.model.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val preferencesUseCase: SetLoginPreferencesUseCase
){
    suspend operator fun invoke(name: String, email:String, pass:String) : BaseResultUseCase<Boolean>{
        return try {
            when(val response = loginRepository.createAccountByEmailAndPass(email,pass)){
                is BaseResultRepository.Error -> BaseResultUseCase.Error(response.exception)
                BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                is BaseResultRepository.Success -> {
                    when(val responseCreate = loginRepository.createUser(response.data.user!!.uid,name,email)){
                        is BaseResultRepository.Error -> BaseResultUseCase.Error(responseCreate.exception)
                        BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                        is BaseResultRepository.Success -> {
                            loginRepository.insertUser(UserModel(response.data.user!!.uid, name,email))
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
