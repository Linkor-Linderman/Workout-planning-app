package com.example.trainingplanapp.featureGroup.presentation.chooseUserScreen

import com.example.trainingplanapp.featureGroup.domain.model.UserInfo

data class ChooseUserScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val name: String = "",
    val userList: List<UserInfo> = emptyList()
)
