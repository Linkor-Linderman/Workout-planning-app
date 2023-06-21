package com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo

data class Training(
    val common: Boolean,
    val complexes: List<ComplexInTrain>,
    val description: String,
    val exercises: List<ExerciseInTrain>,
    val id: String,
    val name: String,
    val published: Boolean
)