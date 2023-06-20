package com.example.trainingplanapp.featureExercises.presentation.allExercisesScreen

import com.example.trainingplanapp.featureExercises.domain.model.ComplexInfo
import com.example.trainingplanapp.featureExercises.domain.model.ExerciseInfo

data class AllExercisesState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val listOfMyExercises: List<ExerciseInfo> = emptyList(),
    val listOfCommonExercises: List<ExerciseInfo> = emptyList(),
    val listOfSetOfExercises: List<ComplexInfo> = emptyList(),
)
