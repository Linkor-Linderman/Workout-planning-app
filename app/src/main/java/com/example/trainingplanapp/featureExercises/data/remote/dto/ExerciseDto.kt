package com.example.trainingplanapp.featureExercises.data.remote.dto

import com.example.trainingplanapp.featureExercises.domain.model.Exercise

data class ExerciseDto(
    val common: Boolean,
    val defaultValues: DefaultValuesDto,
    val description: String,
    val imageId: String,
    val muscleGroups: List<String>,
    val name: String,
    val published: Boolean,
    val trainerId: String
) {
    fun toExercise(): Exercise =
        Exercise(
            common = common,
            defaultValues = defaultValues.toDefaultValues(),
            description = description,
            imageId = imageId,
            muscleGroups, name, published, trainerId
        )
}