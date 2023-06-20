package com.example.trainingplanapp.featureExercises.data.remote.dto

import com.example.trainingplanapp.featureExercises.domain.model.ExerciseBriefInform

data class ExerciseBriefInformDto(
    val exerciseId: String,
    val exerciseValues: DefaultValuesDto
) {
    fun toExerciseBriefInform(): ExerciseBriefInform =
        ExerciseBriefInform(exerciseId = exerciseId, exerciseValues.toDefaultValues())
}