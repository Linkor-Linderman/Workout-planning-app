package com.example.trainingplanapp.featureTrainingScreen.domain.model

data class QueryTraining(
    val common: Boolean,
    val liked: Boolean,
    val my: Boolean,
    val name: String,
    val paginationQueryDto: PaginationQuery,
    val published: Boolean,
    val shared: Boolean
)