package com.example.trainingplanapp.featureExercises.domain.model

data class GetComplex(
    val common: Boolean,
    val liked: Boolean,
    val my: Boolean,
    val name: String,
    val published: Boolean,
    val shared: Boolean,
    val paginationQueryDto: PaginationQuery
)