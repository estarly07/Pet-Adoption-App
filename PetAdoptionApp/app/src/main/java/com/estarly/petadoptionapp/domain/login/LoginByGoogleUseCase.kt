package com.estarly.petadoptionapp.domain.login

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.base.BaseResultUseCase
import com.estarly.petadoptionapp.data.repositories.LoginRepository
import com.estarly.petadoptionapp.data.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginByGoogleUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val setLoginPreferencesUseCase: SetLoginPreferencesUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val userRepository: UserRepository
) {
    /**
     * Inicia sesión mediante Google utilizando el repositorio y retorna el resultado.
     *
     * Esta función suspendida se encarga de gestionar el flujo completo para iniciar sesión mediante Google.
     * Llama al repositorio para autenticar al usuario con el token de identificación de Google proporcionado.
     *
     * @param idToken El token de identificación de Google del usuario.
     * @return Un objeto [BaseResultUseCase] que contiene el resultado de la operación,
     *         con un valor booleano indicando el éxito o fracaso del inicio de sesión.
     *
     */
    suspend operator fun invoke(idToken : String) : BaseResultUseCase<Boolean> {
        return try {
            when(val response = loginRepository.loginByGoogle(idToken)){
                is BaseResultRepository.Error        -> BaseResultUseCase.Error(response.exception)
                BaseResultRepository.NullOrEmptyData -> BaseResultUseCase.NullOrEmptyData
                is BaseResultRepository.Success      -> {
                    when(val responseUser = getUserUseCase(response.data.user!!.uid)){
                        is BaseResultUseCase.Error             -> TODO()
                        BaseResultUseCase.NoInternetConnection -> TODO()
                        BaseResultUseCase.NullOrEmptyData      -> TODO()
                        is BaseResultUseCase.Success           -> {
                            setLoginPreferencesUseCase.setIsLogin(true)
                            userRepository.insertUser(responseUser.data)
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