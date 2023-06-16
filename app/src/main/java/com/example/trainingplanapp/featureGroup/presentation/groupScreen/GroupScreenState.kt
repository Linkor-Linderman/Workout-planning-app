package com.example.trainingplanapp.featureGroup.presentation.groupScreen

import com.example.trainingplanapp.featureGroup.domain.model.GroupInfo

data class GroupScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isCoach: Boolean = false,
    val isFirstButtonChosen: Boolean = true,
    val subordinateGroups: List<GroupInfo> = emptyList(),
    val yourGroups: List<GroupInfo> = emptyList(),
    val name: String = ""
)
