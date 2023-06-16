package com.example.trainingplanapp.featureGroup.data.remote.dto

import com.example.trainingplanapp.featureGroup.domain.model.Group

data class GroupDTO(
    val description: String,
    val id: String,
    val mainTrainer: CoachInfoDTO,
    val name: String,
    val trainerDtos: List<CoachInfoDTO>,
    val users: List<UserInfoDTO>
) {
    fun toGroup(): Group {
        return Group(
            description,
            id,
            mainTrainer.toCoachInfo(),
            name,
            trainerDtos.map { it.toCoachInfo() },
            users.map { it.toUserInfo() }
        )
    }
}