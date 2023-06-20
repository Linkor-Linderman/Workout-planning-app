package com.example.trainingplanapp.featureExercises.presentation.complexInfoScreen

import com.example.trainingplanapp.featureExercises.domain.model.ExerciseInfo

sealed class ComplexInfoUiEvents {
    object DismissAlertDialog : ComplexInfoUiEvents()
    object OnBackArrowButtonClicked : ComplexInfoUiEvents()
    data class OnExerciseClick(val exerciseInfo: ExerciseInfo) : ComplexInfoUiEvents()
}