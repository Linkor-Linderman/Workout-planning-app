package com.example.trainingplanapp.featureExercises.data.remote.dto

data class ExerciseListDto(
    val data: List<ExerciseInfoDto>,
    val maxPage: Int,
    val page: Int,
    val size: Int
)