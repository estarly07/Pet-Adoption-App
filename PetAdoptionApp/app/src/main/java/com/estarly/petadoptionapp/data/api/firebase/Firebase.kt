package com.estarly.petadoptionapp.data.api.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Firebase @Inject constructor(){
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun loginByEmailAndPass(email:String,pass:String) : AuthResult{
        val response =auth.signInWithEmailAndPassword(email,pass)
       return response.await()
    }
}