package com.example.trainingplanapp.featureExercises.data.remote.dto

import com.example.trainingplanapp.featureExercises.domain.model.PaginationQuery

data class PaginationQueryDto(
    val page: Int,
    val size: Int
) {
    fun toPaginationQuery(): PaginationQuery =
        PaginationQuery(page, size)
}