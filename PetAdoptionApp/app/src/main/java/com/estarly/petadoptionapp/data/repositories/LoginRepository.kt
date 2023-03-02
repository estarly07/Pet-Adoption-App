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