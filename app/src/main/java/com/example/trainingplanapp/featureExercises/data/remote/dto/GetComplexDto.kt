package com.example.trainingplanapp.featureExercises.data.remote.dto

data class GetComplexDto(
    val common: Boolean,
    val liked: Boolean,
    val my: Boolean,
    val name: String,
    val published: Boolean,
    val shared: Boolean,
    val paginationQueryDto: PaginationQueryDto
)