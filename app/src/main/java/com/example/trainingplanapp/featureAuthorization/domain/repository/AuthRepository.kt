package com.example.trainingplanapp.featureAuthorization.domain.repository

import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.featureAuthorization.domain.model.RegistrationInfo
import com.example.trainingplanapp.featureAuthorization.domain.model.UserCredentials
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun register(registrationInfo: RegistrationInfo): Flow<Resource<Unit>>

    fun login(userCredentials: UserCredentials): Flow<Resource<Unit>>
}