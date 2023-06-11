package com.example.trainingplanapp.featureMainScreen.data.remote

import com.example.trainingplanapp.featureMainScreen.data.remote.dto.UserInfoDto
import retrofit2.http.GET

interface ProfileApi {
    @GET("/user/me/")
    suspend fun getUserInfo(): UserInfoDto
}