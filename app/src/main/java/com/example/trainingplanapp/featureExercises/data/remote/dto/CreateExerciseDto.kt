package com.example.trainingplanapp.featureExercises.data.remote.dto

data class CreateExerciseDto(
    val defaultValues: DefaultValuesDto,
    val description: String,
    val imageId: String,
    val muscleGroups: List<String>,
    val name: String,
    val published: Boolean
)