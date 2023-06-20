package com.example.trainingplanapp.featureExercises.domain.model

data class CreateExercise(
    val defaultValues: DefaultValues,
    val description: String,
    val imageId: String,
    val muscleGroups: List<String>,
    val name: String,
    val published: Boolean
)