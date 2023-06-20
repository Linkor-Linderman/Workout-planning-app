package com.example.trainingplanapp.featureExercises.domain.model

data class ExerciseInfo(
    val id: String,
    val imageId: String,
    val muscleGroup: List<String>,
    val name: String
)