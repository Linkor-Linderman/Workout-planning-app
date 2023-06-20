package com.example.trainingplanapp.featureExercises.data.remote.dto

data class GetExerciseDto(
    val common: Boolean,
    val liked: Boolean,
    val muscleGroups: List<String>,
    val my: Boolean,
    val name: String,
    val paginationQueryDto: PaginationQueryDto,
    val published: Boolean,
    val shared: Boolean
)

