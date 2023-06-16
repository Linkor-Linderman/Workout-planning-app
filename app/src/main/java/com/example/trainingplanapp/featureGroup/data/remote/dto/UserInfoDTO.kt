package com.example.trainingplanapp.featureGroup.data.remote.dto

import com.example.trainingplanapp.featureGroup.domain.model.UserInfo

data class UserInfoDTO(
    val id: String,
    val imageId: String?,
    val login: String,
    val name: String
) {
    fun toUserInfo(): UserInfo =
        UserInfo(id, imageId ?: "", login, name)
}