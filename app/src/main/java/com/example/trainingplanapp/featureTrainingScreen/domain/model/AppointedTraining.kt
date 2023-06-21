package com.example.trainingplanapp.featureTrainingScreen.domain.model

data class AppointedTraining(
    val dates: List<String>,
    val id: String,
    val name: String,
    val trainerName: String
)