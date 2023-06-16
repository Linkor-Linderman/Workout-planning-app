package com.example.trainingplanapp.featureGroup.data.remote.dto

data class RequestsListDTO(
    val maxPage: Int,
    val page: Int,
    val size: Int,
    val data: List<RequestInfoDTO>
)