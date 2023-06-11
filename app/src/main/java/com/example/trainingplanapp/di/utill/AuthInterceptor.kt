package com.example.trainingplanapp.di.utill

import com.example.trainingplanapp.common.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sharedPref: SharedPref
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPref.getString(Constants.ACCESS_TOKEN)
        val request = chain.request().newBuilder().apply {
            token?.let {
                addHeader("Authorization", it)
            }
        }.build()
        return chain.proceed(request)
    }
}
