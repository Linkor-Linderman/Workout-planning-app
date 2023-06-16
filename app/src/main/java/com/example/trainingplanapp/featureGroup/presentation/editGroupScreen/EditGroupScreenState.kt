package com.example.trainingplanapp.featureGroup.presentation.editGroupScreen

import com.example.trainingplanapp.featureGroup.domain.model.UserInfo

data class EditGroupScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val name: String = "",
    val description: String = "",
    val userInfoList: List<UserInfo> = emptyList(),
    val groupId: String = ""
)
