package com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo

data class ExerciseInTrain(
    val exerciseId: String,
    val exerciseValues: ExerciseValues,
    val imageId: String,
    val name: String,
    val orderNumber: Int
): TrainingListItem