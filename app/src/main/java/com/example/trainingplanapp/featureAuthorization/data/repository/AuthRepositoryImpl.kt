package com.example.trainingplanapp.featureAuthorization.data.repository

import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Constants
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.di.utill.SharedPref
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureAuthorization.data.remote.AuthorizationApi
import com.example.trainingplanapp.featureAuthorization.data.remote.dto.LoginDto
import com.example.trainingplanapp.featureAuthorization.data.remote.dto.RegisterDto
import com.example.trainingplanapp.featureAuthorization.domain.model.RegistrationInfo
import com.example.trainingplanapp.featureAuthorization.domain.model.UserCredentials
import com.example.trainingplanapp.featureAuthorization.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val api: AuthorizationApi,
    private val sharedPreferences: SharedPref,
    private val stringResourcesManager: StringResourcesManager

) : AuthRepository {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)
    private val connectionErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.no_connection_error)

    override fun register(registrationInfo: RegistrationInfo): Flow<Resource<Unit>> =
        flow {
            emit(Resource.Loading())
            try {
                api.register(
                    RegisterDto(
                        registrationInfo.email, registrationInfo.login,
                        registrationInfo.name, registrationInfo.password
                    )
                )
                emit(Resource.Success(Unit))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: unexpectedErrorMessage
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error(connectionErrorMessage))
            }
        }.flowOn(Dispatchers.IO)

    override fun login(userCredentials: UserCredentials): Flow<Resource<Unit>> =
        flow {
            emit(Resource.Loading())
            try {
                api.login(
                    LoginDto(
                        userCredentials.login,
                        userCredentials.password
                    )
                ).let { tokenDto ->
                    sharedPreferences.setString(Constants.ACCESS_TOKEN, tokenDto.accessToken)
                    sharedPreferences.setString(Constants.REFRESH_TOKEN, tokenDto.restoreToken)
                    emit(Resource.Success(Unit))

                }
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        e.localizedMessage ?: unexpectedErrorMessage
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error(connectionErrorMessage))
            }
        }.flowOn(Dispatchers.IO)
}