package com.example.trainingplanapp.featureMainScreen.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.trainingplanapp.featureMainScreen.data.remote.ProfileApi
import com.example.trainingplanapp.featureMainScreen.domain.model.UserInfo
import com.example.trainingplanapp.featureMainScreen.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val api: ProfileApi,
) : ProfileRepository {
    override suspend fun getUserInfo(): UserInfo {
        return api.getUserInfo().toUserInfo()
    }

    override suspend fun downloadPhoto(id: String?): Bitmap? {
        return if (id == null) {
            null
        } else {
            BitmapFactory.decodeStream(api.getImageById(id).byteStream())
        }
    }
}