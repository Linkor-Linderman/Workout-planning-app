package com.example.trainingplanapp.featureExercises.presentation.exerciseInfoScreen

import com.example.trainingplanapp.featureExercises.domain.model.Exercise

data class ExerciseInfoState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val exercise: Exercise
)
