package com.example.trainingplanapp.featureExercises.data.remote.dto

data class ComplexListDto(
    val data: List<ComplexInfoDto>,
    val maxPage: Int,
    val page: Int,
    val size: Int
)