package com.example.trainingplanapp.featureGroup.data.remote.dto

data class CoachInfoListDTO(
    val maxPage: Int,
    val page: Int,
    val size: Int,
    val data: List<CoachInfoDTO>
)