package com.example.trainingplanapp.featureAuthorization.data.remote

import com.example.trainingplanapp.featureAuthorization.data.remote.dto.LoginDto
import com.example.trainingplanapp.featureAuthorization.data.remote.dto.RegisterDto
import com.example.trainingplanapp.featureAuthorization.data.remote.dto.TokenDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationApi {
    @POST("/user/register/")
    suspend fun register(@Body registerDto: RegisterDto)

    @POST("/user/sing-in/")
    suspend fun login(@Body loginDto: LoginDto): TokenDto
}