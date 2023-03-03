package com.estarly.petadoptionapp.data.api.firebase

import android.util.Log
import com.estarly.petadoptionapp.data.api.response.UserResponse
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
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

    suspend fun createUser(user: UserResponse): Boolean {
        val response = dbFirestore.collection("users").document(user.id).set(user.toMap())
        response.await()
        return response.isSuccessful
    }

    suspend fun getUser(uid: String): DocumentSnapshot {
        Log.i("TAG",uid)
        val response = dbFirestore.collection("users").document(uid).get()
        return  response.await()
    }

    suspend fun updateCart(uid: String, data: Map<String, *>): Boolean {
        Log.i("updateCart", uid)
        val response = dbFirestore.collection("carts").document(uid).set(data)
        response.await()
        return response.isSuccessful
    }

    suspend fun getCart(uid: String):DocumentSnapshot {
        val response = dbFirestore.collection("carts").document(uid).get()
        return  response.await()
    }

    suspend fun deleteCart(uid: String) : Boolean {
        val response = dbFirestore.collection("carts").document(uid).delete()
        response.await()
        return  response.isSuccessful
    }

    suspend fun increaseProductCart(uid: String,data: Map<String,*>): Boolean {
        val response = dbFirestore.collection("carts").document(uid).set(data)
        response.await()
        return response.isSuccessful
    }
}