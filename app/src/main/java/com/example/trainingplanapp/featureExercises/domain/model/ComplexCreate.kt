package com.example.trainingplanapp.featureExercises.domain.model

data class ComplexCreate(
    val complexType: String,
    val description: String,
    val exercises: List<ExerciseBriefInform>,
    val name: String,
    val published: Boolean,
    val repetitions: Int,
    val spaceDuration: Int
)