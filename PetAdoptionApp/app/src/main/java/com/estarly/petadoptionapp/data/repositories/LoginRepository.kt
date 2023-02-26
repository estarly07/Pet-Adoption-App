package com.estarly.petadoptionapp.data.repositories

import android.util.Log
import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.data.api.firebase.Firebase
import kotlinx.coroutines.tasks.await
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
}