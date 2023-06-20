package com.example.trainingplanapp.featureExercises.data.remote.dto

import com.example.trainingplanapp.featureExercises.domain.model.ComplexInfo

data class ComplexInfoDto(
    val common: Boolean,
    val complexType: String,
    val id: String,
    val name: String
) {
    fun toComplexInfo(): ComplexInfo =
        ComplexInfo(common, complexType, id, name)
}