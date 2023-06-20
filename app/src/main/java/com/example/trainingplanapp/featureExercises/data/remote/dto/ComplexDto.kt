package com.example.trainingplanapp.featureExercises.data.remote.dto

import com.example.trainingplanapp.featureExercises.domain.model.Complex

data class ComplexDto(
    val common: Boolean,
    val complexType: String,
    val description: String,
    val exercises: List<ExerciseBriefInformDto>,
    val id: String,
    val name: String,
    val published: Boolean,
    val repetitions: Int,
    val spaceDuration: Int,
    val template: Boolean
) {
    fun toComplex(): Complex =
        Complex(
            common,
            complexType,
            description,
            exercises = exercises.map { it.toExerciseBriefInform() },
            exercisesInformation = emptyList(),
            id, name, published, repetitions, spaceDuration, template
        )
}