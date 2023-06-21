package com.example.trainingplanapp.featureExercises.data.remote.dto

import com.example.trainingplanapp.featureExercises.domain.model.ExerciseBriefInform

data class ExerciseBriefInformDto(
    val exerciseId: String,
    val exerciseValues: DefaultValuesDto,
    val imageId: String,
    val name: String,
    val orderNumber: Int
) {
    fun toExerciseBriefInform(): ExerciseBriefInform =
        ExerciseBriefInform(
            exerciseId = exerciseId,
            exerciseValues.toDefaultValues(),
            imageId,
            name,
            orderNumber
        )
}