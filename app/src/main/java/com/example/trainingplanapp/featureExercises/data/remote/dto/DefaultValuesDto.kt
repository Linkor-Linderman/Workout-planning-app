package com.example.trainingplanapp.featureExercises.data.remote.dto

import com.example.trainingplanapp.featureExercises.domain.model.DefaultValues

data class DefaultValuesDto(
    val duration: Int,
    val repetitions: Int,
    val weight: Int
) {
    fun toDefaultValues(): DefaultValues =
        DefaultValues(duration, repetitions, weight)
}