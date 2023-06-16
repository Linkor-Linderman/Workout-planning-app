package com.example.trainingplanapp.featureGroup.presentation.confirmRequestScreen

import com.example.trainingplanapp.featureGroup.domain.model.RequestInfo

data class ConfirmRequestsScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val name: String = "",
    val requestList: List<RequestInfo> = emptyList(),
)
