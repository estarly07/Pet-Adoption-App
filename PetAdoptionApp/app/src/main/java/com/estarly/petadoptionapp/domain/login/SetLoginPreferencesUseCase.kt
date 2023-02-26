package com.estarly.petadoptionapp.domain.login

import com.estarly.petadoptionapp.data.preferences.Preferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetLoginPreferencesUseCase @Inject constructor(
    private val preferences: Preferences
){
    fun setIsLogin(isLogin : Boolean){
        preferences.setIsLogin(isLogin)
    }
    fun getIsLogin() = preferences.getIsLogin()
}