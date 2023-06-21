package com.example.trainingplanapp.featureTrainingScreen.presentation.trainInfoScreen

sealed class TrainInfoUiEvents {
    object DismissAlertDialog : TrainInfoUiEvents()
    object ClickToNavigateBack : TrainInfoUiEvents()
    object ClickToPlay : TrainInfoUiEvents()
}