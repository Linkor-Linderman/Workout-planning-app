package com.example.trainingplanapp.featureGroup.data.remote.dto

import com.example.trainingplanapp.featureGroup.domain.model.GroupCreate

data class GroupCreateDto(
    val description: String,
    val name: String,
    val trainers: List<String>,
    val users: List<String>
) {
    fun toGroupCreate(): GroupCreate {
        return GroupCreate(description, name, trainers, users)
    }

}