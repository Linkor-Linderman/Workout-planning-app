package com.example.trainingplanapp.featureTrainingScreen.data.remote.dto

data class QueryTrainingDto(
    val common: Boolean,
    val liked: Boolean,
    val my: Boolean,
    val name: String,
    val paginationQueryDto: PaginationQueryDto,
    val published: Boolean,
    val shared: Boolean
)