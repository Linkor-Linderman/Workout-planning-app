package com.example.trainingplanapp.featureAuthorization.domain.useCases

import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.featureAuthorization.domain.model.UserCredentials
import com.example.trainingplanapp.featureAuthorization.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(userCredentials: UserCredentials): Flow<Resource<Unit>> {
        return repository.login(userCredentials)
    }
}