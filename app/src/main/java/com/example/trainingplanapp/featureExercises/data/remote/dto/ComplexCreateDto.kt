package com.example.trainingplanapp.featureExercises.data.remote.dto

data class ComplexCreateDto(
    val complexType: String,
    val description: String,
    val exercises: List<ExerciseBriefInformDto>,
    val name: String,
    val published: Boolean,
    val repetitions: Int,
    val spaceDuration: Int
)