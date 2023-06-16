package com.example.trainingplanapp.featureGroup.data.remote.dto

import com.example.trainingplanapp.featureGroup.domain.model.RequestInfo

data class RequestInfoDTO(
    val login: String,
    val name: String,
    val queryId: String
) {
    fun toRequestInfo(): RequestInfo =
        RequestInfo(login, name, queryId)
}