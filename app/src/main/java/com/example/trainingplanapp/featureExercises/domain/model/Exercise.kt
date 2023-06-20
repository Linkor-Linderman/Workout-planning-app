package com.example.trainingplanapp.featureExercises.domain.model

data class Exercise(
    val common: Boolean,
    val defaultValues: DefaultValues,
    val description: String,
    val imageId: String,
    val muscleGroups: List<String>,
    val name: String,
    val published: Boolean,
    val trainerId: String
)