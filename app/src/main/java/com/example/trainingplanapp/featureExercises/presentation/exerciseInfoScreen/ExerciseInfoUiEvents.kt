package com.example.trainingplanapp.featureExercises.presentation.exerciseInfoScreen

sealed class ExerciseInfoUiEvents {
    object DismissAlertDialog : ExerciseInfoUiEvents()
    object OnBackArrowButtonClicked : ExerciseInfoUiEvents()
}