package com.example.trainingplanapp.featureExercises.domain.model

data class GetExercise(
    val common: Boolean,
    val liked: Boolean,
    val muscleGroups: List<String>,
    val my: Boolean,
    val name: String,
    val paginationQueryDto: PaginationQuery,
    val shared: Boolean,
    val published: Boolean
)

