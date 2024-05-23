package com.estarly.petadoptionapp.data.repositories

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.data.api.firebase.Firebase
import com.estarly.petadoptionapp.data.api.response.UserResponse
import com.estarly.petadoptionapp.data.database.dao.UserDao
import com.estarly.petadoptionapp.data.database.entities.UserEntity
import com.estarly.petadoptionapp.domain.model.UserModel
import com.google.firebase.auth.AuthResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val firebase: Firebase,
    private val userDao: UserDao
){

    suspend fun loginByEmailAndPass(email:String, pass : String) : BaseResultRepository<AuthResult>{
        return try {
            val response = firebase.loginByEmailAndPass(email,pass)
            if(response.user==null){
                BaseResultRepository.NullOrEmptyData
            }else
                BaseResultRepository.Success(response)
        }catch (e : Exception){
            BaseResultRepository.Error(e)
        }
    }
    suspend fun createAccountByEmailAndPass(email:String, pass : String) : BaseResultRepository<AuthResult>{
        return try {
            val response = firebase.createByEmailAndPass(email,pass)
            if(response.user==null){
                BaseResultRepository.NullOrEmptyData
            }else
                BaseResultRepository.Success(response)
        }catch (e : Exception){
            BaseResultRepository.Error(e)
        }
    }

    suspend fun createUser(uid: String, name: String, email: String) : BaseResultRepository<Boolean> {
        return try {
            val user = UserResponse(uid,name,email)
            val response = firebase.createUser(user)
            BaseResultRepository.Success(response)
        }catch (e : Exception){
            BaseResultRepository.Error(e)
        }
    }
    suspend fun getUserApi(uid: String) : BaseResultRepository<UserModel> {
        return try {
            val response = firebase.getUser(uid)
            if(response.data == null){
                BaseResultRepository.NullOrEmptyData
            }else{
                val user = response.data!!.toData()
                BaseResultRepository.Success(user.toData())
            }
        }catch (e : Exception){
            BaseResultRepository.Error(e)
        }
    }
    /**
     *
     * Esta función inicia sesión en Firebase usando Google con el token de identificación proporcionado.
     *
     * Esta función se utiliza para iniciar sesión en Firebase mediante un token de identificación
     * de Google. La función intenta autenticar al usuario llamando al método `loginByGoogle` de Firebase con el
     * token proporcionado. Si la autenticación es exitosa y el usuario no es nulo, se devuelve un resultado de éxito.
     * Si la autenticación falla o el usuario es nulo, se devuelve un resultado de error o datos vacíos, respectivamente.
     *
     * @param idToken El token de identificación de Google para autenticar al usuario.
     * @return Un objeto `BaseResultRepository<AuthResult>` que representa el resultado de la operación de inicio de sesión.
     */
    suspend fun loginByGoogle(idToken: String): BaseResultRepository<AuthResult> {
        return try {
            val response = firebase.loginByGoogle(idToken)
            if(response.user == null){
                BaseResultRepository.NullOrEmptyData
            }else
                BaseResultRepository.Success(response)
        }catch (e : Exception){
            BaseResultRepository.Error(e)
        }
    }

}
fun UserEntity.toData() : UserModel =
    UserModel(
        id, name,email
    )
fun UserResponse.toData() : UserModel =
    UserModel(
        id, name,email
    )
fun Map<String, *>.toData() : UserResponse =
    UserResponse(
        this["id"].toString(), this["name"].toString(),this["email"].toString()
    )