package com.example.trainingplanapp.featureGroup.presentation.makeGroupScreen

import com.example.trainingplanapp.featureGroup.domain.model.UserInfo

data class MakeGroupScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val name: String = "",
    val description: String = "",
    val userInfoList: List<UserInfo> = emptyList()
    )
