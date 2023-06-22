package com.example.trainingplanapp.featureMainScreen.domain.useCases

data class ProfileUseCase(
    val getProfileUseCase: GetProfileUseCase,
    val getCurrentDateUseCase: GetCurrentDateUseCase,
    val getPhotoUseCase: GetPhotoUseCase
)
