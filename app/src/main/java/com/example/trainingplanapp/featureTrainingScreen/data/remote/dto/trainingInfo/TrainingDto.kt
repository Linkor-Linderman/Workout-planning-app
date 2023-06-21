package com.example.trainingplanapp.featureTrainingScreen.data.remote.dto.trainingInfo

import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ComplexInTrain
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ExerciseInTrain

data class TrainingDto(
    val common: Boolean,
    val complexes: List<ComplexInTrain>,
    val description: String,
    val exercises: List<ExerciseInTrain>,
    val id: String,
    val name: String,
    val published: Boolean
)