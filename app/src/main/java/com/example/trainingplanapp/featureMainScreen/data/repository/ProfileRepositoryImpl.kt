package com.example.trainingplanapp.featureMainScreen.data.repository

import com.example.trainingplanapp.featureMainScreen.data.remote.ProfileApi
import com.example.trainingplanapp.featureMainScreen.domain.model.UserInfo
import com.example.trainingplanapp.featureMainScreen.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val api: ProfileApi
) : ProfileRepository {
    override suspend fun getUserInfo(): UserInfo {
        return api.getUserInfo().toUserInfo()
    }
}