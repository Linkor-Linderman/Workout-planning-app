package com.example.trainingplanapp.di

import com.example.trainingplanapp.di.utill.SharedPref
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureAuthorization.data.remote.AuthorizationApi
import com.example.trainingplanapp.featureAuthorization.data.repository.AuthRepositoryImpl
import com.example.trainingplanapp.featureAuthorization.domain.repository.AuthRepository
import com.example.trainingplanapp.featureAuthorization.domain.useCases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeatureAuthModule {
    @Provides
    @Singleton
    fun provideAuthApi(
        retrofit: Retrofit
    ): AuthorizationApi {
        return retrofit.create(AuthorizationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthorizationApi,
        sharedPreferences: SharedPref,
        stringResourcesManager: StringResourcesManager
    ): AuthRepository {
        return AuthRepositoryImpl(api, sharedPreferences, stringResourcesManager)
    }

    @Provides
    @Singleton
    fun provideAuthUseCases(
        repository: AuthRepository,
        sharedPreferences: SharedPref
    ): AuthUseCases {
        return AuthUseCases(
            loginUseCase = LoginUseCase(repository),
            registrationUseCase = RegistrationUseCase(repository),
            passwordValidationUseCase = PasswordValidationUseCase(),
            emailValidationUseCase = EmailValidationUseCase(),
            loginValidationUseCase = LoginValidationUseCase(),
            emptyFieldsValidationUseCase = EmptyFieldsValidationUseCase()
        )
    }

}