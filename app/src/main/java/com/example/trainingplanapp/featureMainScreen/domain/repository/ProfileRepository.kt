package com.example.trainingplanapp.featureMainScreen.domain.repository

import android.graphics.Bitmap
import com.example.trainingplanapp.featureMainScreen.domain.model.UserInfo

interface ProfileRepository {
    suspend fun getUserInfo(): UserInfo
    suspend fun downloadPhoto(id: String?): Bitmap?
}