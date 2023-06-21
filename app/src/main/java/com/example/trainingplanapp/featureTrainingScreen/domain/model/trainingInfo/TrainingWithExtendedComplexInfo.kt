package com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo

data class TrainingWithExtendedComplexInfo(
    val common: Boolean,
    val complexes: List<ComplexInTrainExtended>,
    val description: String,
    val exercises: List<ExerciseInTrain>,
    val id: String,
    val name: String?,
    val published: Boolean
)
