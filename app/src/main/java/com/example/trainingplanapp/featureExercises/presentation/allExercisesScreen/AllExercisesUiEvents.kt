package com.example.trainingplanapp.featureExercises.presentation.allExercisesScreen

import com.example.trainingplanapp.featureExercises.domain.model.ComplexInfo
import com.example.trainingplanapp.featureExercises.domain.model.ExerciseInfo

sealed class AllExercisesUiEvents {
    object DismissAlertDialog : AllExercisesUiEvents()
    data class ClickToExercise(val exerciseInfo: ExerciseInfo) : AllExercisesUiEvents()
    data class ClickToComplex(val complex: ComplexInfo) : AllExercisesUiEvents()
    object OnAddExerciseButtonClick : AllExercisesUiEvents()
}