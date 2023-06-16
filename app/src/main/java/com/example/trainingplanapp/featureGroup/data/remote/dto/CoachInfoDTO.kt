package com.example.trainingplanapp.featureGroup.data.remote.dto

import com.example.trainingplanapp.featureGroup.domain.model.CoachInfo

data class CoachInfoDTO(
    val id: String,
    val name: String,
    val shortName: String
) {
    fun toCoachInfo(): CoachInfo =
        CoachInfo(id, name, shortName)
}