package com.example.trainingplanapp.featureTrainingScreen.data.remote.dto.trainingInfo

import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ExerciseValues

data class ExerciseValueDto(
    val exerciseId: String,
    val exerciseValues: ExerciseValues,
    val imageId: String,
    val name: String,
    val orderNumber: Int
)