package com.example.trainingplanapp.featureTrainingScreen.domain.useCase

data class TrainingUseCases(
    val getAppointedTrainingsUseCase: GetAppointedTrainingsUseCase,
    val getTrainingListUseCase: GetTrainingListUseCase,
    val getTrainingByIdUseCase: GetTrainingByIdUseCase,
    val getAppointedTrainingByIdUseCase: GetAppointedTrainingByIdUseCase,
    val getSortedByOrderTrainingItemInListUseCase: GetSortedByOrderTrainingItemInListUseCase

)
