package com.example.data

import okhttp3.Interceptor
import okhttp3.Response

class MyTokenInterceptor(private val token: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .addHeader("Authorization", "Token $token")
            .build()
        return chain.proceed(authenticatedRequest)
    }
}