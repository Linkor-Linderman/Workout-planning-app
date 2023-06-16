package com.example.trainingplanapp.featureGroup.domain.model

data class Group(
    val description: String,
    val id: String,
    val mainTrainer: CoachInfo,
    val name: String,
    val trainerDtos: List<CoachInfo>,
    val users: List<UserInfo>
)