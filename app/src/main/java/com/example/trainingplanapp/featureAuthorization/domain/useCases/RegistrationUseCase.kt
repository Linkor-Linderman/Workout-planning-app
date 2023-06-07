package com.example.trainingplanapp.featureAuthorization.domain.useCases

import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.featureAuthorization.domain.model.RegistrationInfo
import com.example.trainingplanapp.featureAuthorization.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class RegistrationUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(registrationInfo: RegistrationInfo): Flow<Resource<Unit>> {
        return repository.register(registrationInfo)
    }
}