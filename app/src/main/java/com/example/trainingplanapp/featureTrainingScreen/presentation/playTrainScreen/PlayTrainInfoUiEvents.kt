package com.example.trainingplanapp.featureTrainingScreen.presentation.playTrainScreen

import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ComplexInTrainExtended
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ExerciseInTrain

sealed class PlayTrainInfoUiEvents {
    object DismissAlertDialog : PlayTrainInfoUiEvents()
    object ClickToNavigateBack : PlayTrainInfoUiEvents()
    data class OnComplexCompleteButtonClick(val complexInTrain: ComplexInTrainExtended): PlayTrainInfoUiEvents()
    data class OnExerciseCompleteInComplexButtonClick(
        val complexInTrain: ComplexInTrainExtended,
        val exerciseInTrain: ExerciseInTrain
    ): PlayTrainInfoUiEvents()
    data class OnExerciseCompleteButtonClick(val exercise: ExerciseInTrain): PlayTrainInfoUiEvents()
    object ClickToCompleteAll : PlayTrainInfoUiEvents()
}