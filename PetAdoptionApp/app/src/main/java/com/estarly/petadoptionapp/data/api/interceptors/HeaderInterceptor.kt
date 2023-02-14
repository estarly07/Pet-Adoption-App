package com.estarly.petadoptionapp.data.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val resquest = chain.request().newBuilder().addHeader(
            "Content-Type","application/json"
        )
        return chain.proceed(resquest.build())
    }
}