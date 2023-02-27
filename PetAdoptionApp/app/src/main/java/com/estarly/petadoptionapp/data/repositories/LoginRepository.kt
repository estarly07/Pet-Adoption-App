package com.estarly.petadoptionapp.data.repositories

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.data.api.firebase.Firebase
import com.estarly.petadoptionapp.data.api.response.UserResponse
import com.google.firebase.auth.AuthResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val firebase: Firebase
){

    suspend fun loginByEmailAndPass(email:String, pass : String) : BaseResultRepository<Boolean>{
        return try {
            val response = firebase.loginByEmailAndPass(email,pass)
            BaseResultRepository.Success(true)
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
            if(response == null){
                BaseResultRepository.NullOrEmptyData
            }else
                BaseResultRepository.Success(true)
        }catch (e : Exception){
            BaseResultRepository.Error(e)
        }
    }
}