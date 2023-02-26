package com.estarly.petadoptionapp.data.preferences

import android.content.Context

class Preferences(
    private val context: Context
) {
    private val isLogin = "isLogin"
    private val preferencesName = "preferences"
    fun setIsLogin(isLogin : Boolean){
        val sharedPreferences = context.getSharedPreferences(preferencesName,Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(this.isLogin,isLogin)
        editor.apply();
    }
    fun getIsLogin() : Boolean{
        return context.getSharedPreferences(preferencesName,Context.MODE_PRIVATE)
            .getBoolean(isLogin,false)
    }
}