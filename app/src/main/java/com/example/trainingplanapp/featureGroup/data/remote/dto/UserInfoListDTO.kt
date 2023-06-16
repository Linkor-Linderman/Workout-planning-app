package com.example.trainingplanapp.featureGroup.data.remote.dto

data class UserInfoListDTO(
    val maxPage: Int,
    val page: Int,
    val size: Int,
    val data: List<UserInfoDTO>
)