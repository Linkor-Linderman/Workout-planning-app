package com.example.trainingplanapp.featureExercises.domain.model

data class Complex(
    val common: Boolean,
    val complexType: String,
    val description: String,
    val exercises: List<ExerciseBriefInform>,
    val exercisesInformation: List<ExerciseInfo>,
    val id: String,
    val name: String,
    val published: Boolean,
    val repetitions: Int,
    val spaceDuration: Int,
    val template: Boolean
)