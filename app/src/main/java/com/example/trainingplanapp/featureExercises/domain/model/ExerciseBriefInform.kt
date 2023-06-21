package com.example.trainingplanapp.featureExercises.domain.model

data class ExerciseBriefInform(
    val exerciseId: String,
    val exerciseValues: DefaultValues,
    val imageId: String,
    val name: String,
    val orderNumber: Int
)