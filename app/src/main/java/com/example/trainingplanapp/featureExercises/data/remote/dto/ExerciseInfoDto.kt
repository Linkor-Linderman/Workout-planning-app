package com.example.trainingplanapp.featureExercises.data.remote.dto

import com.example.trainingplanapp.featureExercises.domain.model.ExerciseInfo

data class ExerciseInfoDto(
    val id: String,
    val imageId: String,
    val muscleGroup: List<String>,
    val name: String
) {
    fun toExerciseInfo(): ExerciseInfo =
        ExerciseInfo(id, imageId, muscleGroup, name)
}
