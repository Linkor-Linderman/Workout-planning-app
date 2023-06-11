package com.example.trainingplanapp.featureMainScreen.domain.repository

import com.example.trainingplanapp.featureMainScreen.domain.model.UserInfo

interface ProfileRepository {
    suspend fun getUserInfo(): UserInfo
}