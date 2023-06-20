package com.example.trainingplanapp.featureExercises.presentation.complexInfoScreen

import com.example.trainingplanapp.featureExercises.domain.model.Complex

data class ComplexInfoState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val complex: Complex
)
