package com.example.trainingplanapp.di

import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureMainScreen.data.remote.ProfileApi
import com.example.trainingplanapp.featureMainScreen.data.repository.ProfileRepositoryImpl
import com.example.trainingplanapp.featureMainScreen.domain.repository.ProfileRepository
import com.example.trainingplanapp.featureMainScreen.domain.useCases.GetCurrentDateUseCase
import com.example.trainingplanapp.featureMainScreen.domain.useCases.GetProfileUseCase
import com.example.trainingplanapp.featureMainScreen.domain.useCases.ProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeatureMainModule {
    @Provides
    @Singleton
    fun provideProfileApi(
        retrofit: Retrofit
    ): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        api: ProfileApi,
    ): ProfileRepository {
        return ProfileRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(
        repository: ProfileRepository,
        stringResourcesManager: StringResourcesManager
    ): ProfileUseCase {
        return ProfileUseCase(
            getProfileUseCase = GetProfileUseCase(repository, stringResourcesManager),
            getCurrentDateUseCase = GetCurrentDateUseCase()
        )
    }
}