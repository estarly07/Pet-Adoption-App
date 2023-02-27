package com.estarly.petadoptionapp.data.api.firebase

import com.estarly.petadoptionapp.data.api.response.UserResponse
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Firebase @Inject constructor(){
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val dbFirestore = Firebase.firestore

    suspend fun loginByEmailAndPass(email:String,pass:String) : AuthResult{
        val response =auth.signInWithEmailAndPassword(email,pass)
       return response.await()
    }

    suspend fun createByEmailAndPass(email:String,pass:String) : AuthResult{
        val response =auth.createUserWithEmailAndPassword(email,pass)
        return response.await()
    }

    suspend fun createUser(user: UserResponse): DocumentReference {
        val response = dbFirestore.collection("users").add(user.toMap())
        return  response.await()
    }
}