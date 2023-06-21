package com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo

data class ComplexInTrain(
    val complexId: String,
    val exerciseValues: List<ExerciseInTrain>,
    val orderNumber: Int
) : TrainingListItem