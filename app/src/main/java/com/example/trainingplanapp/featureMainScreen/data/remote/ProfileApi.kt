package com.example.trainingplanapp.featureMainScreen.data.remote

import com.example.trainingplanapp.featureMainScreen.data.remote.dto.UserInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileApi {
    @GET("/user/me/")
    suspend fun getUserInfo(): UserInfoDto

    @GET("/image/{id}")
    suspend fun getImageById(
        @Path("id") imageId: String
    ): ResponseBody
}