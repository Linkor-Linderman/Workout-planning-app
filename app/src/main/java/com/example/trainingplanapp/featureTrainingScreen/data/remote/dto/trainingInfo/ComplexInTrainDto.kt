package com.example.trainingplanapp.featureTrainingScreen.data.remote.dto.trainingInfo

import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ExerciseValue

data class ComplexInTrainDto(
    val complexId: String,
    val exerciseValues: List<ExerciseValue>,
    val orderNumber: Int
)