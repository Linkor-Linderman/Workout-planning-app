package com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo

data class ComplexInTrainExtended(
    val complexId: String,
    val name: String,
    val description: String,
    val exerciseValues: List<ExerciseInTrain>,
    val orderNumber: Int
) : TrainingListItem