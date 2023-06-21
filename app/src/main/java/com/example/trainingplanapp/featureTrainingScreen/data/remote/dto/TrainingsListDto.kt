package com.example.trainingplanapp.featureTrainingScreen.data.remote.dto

data class TrainingsListDto(
    val maxPage: Int,
    val page: Int,
    val size: Int,
    val data: List<TrainingInfoDto>
)