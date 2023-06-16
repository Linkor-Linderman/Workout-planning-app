package com.example.trainingplanapp.featureGroup.domain.model

data class GroupCreate(
    val description: String,
    val name: String,
    val trainers: List<String>,
    val users: List<String>
)