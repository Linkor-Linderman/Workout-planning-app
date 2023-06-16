package com.example.trainingplanapp.featureGroup.domain.useCase

data class CoachUseCases(
    val getCoachListUseCase: GetCoachListUseCase,
    val becomeCoachUseCase: BecomeCoachUseCase,
    val requestsUseCase: GetRequestsUseCase,
    val addToCoachUseCase: AddToCoachUseCase,
    val acceptRequestUseCase: AcceptRequestUseCase,
    val dismissRequestUseCase: DismissRequestUseCase,
    val getUsersUseCase: GetUsersUseCase
)
