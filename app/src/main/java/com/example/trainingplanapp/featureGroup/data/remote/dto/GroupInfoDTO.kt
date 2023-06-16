package com.example.trainingplanapp.featureGroup.data.remote.dto

import com.example.trainingplanapp.featureGroup.domain.model.GroupInfo

data class GroupInfoDTO(
    val id: String,
    val mainTrainerName: String,
    val name: String,
    val personCount: Int
) {
    fun toGroupInfo(): GroupInfo =
        GroupInfo(id, mainTrainerName, name, personCount)
}