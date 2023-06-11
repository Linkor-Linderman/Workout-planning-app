package com.example.trainingplanapp.featureMainScreen.data.remote.dto

import com.example.trainingplanapp.featureMainScreen.domain.model.UserInfo

data class UserInfoDto(
    val email: String,
    val isTrainer: Boolean,
    val login: String,
    val name: String
) {
    fun toUserInfo(): UserInfo {
        return UserInfo(email, isTrainer, login, name)
    }
}