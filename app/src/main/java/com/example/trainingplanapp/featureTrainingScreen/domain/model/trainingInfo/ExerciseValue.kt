package com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo

data class ExerciseValue(
    val exerciseId: String,
    val exerciseValues: ExerciseValues,
    val imageId: String,
    val name: String,
    val orderNumber: Int
)