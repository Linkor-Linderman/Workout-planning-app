package com.example.trainingplanapp.di

import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureExercises.domain.repository.ComplexRepository
import com.example.trainingplanapp.featureTrainingScreen.data.remote.TrainingApi
import com.example.trainingplanapp.featureTrainingScreen.data.repostiory.TrainingRepositoryImpl
import com.example.trainingplanapp.featureTrainingScreen.domain.repository.TrainingRepository
import com.example.trainingplanapp.featureTrainingScreen.domain.useCase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeatureTrainingModule {
    @Provides
    @Singleton
    fun provideTrainingApi(
        retrofit: Retrofit
    ): TrainingApi {
        return retrofit.create(TrainingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTrainingRepository(
        api: TrainingApi,
    ): TrainingRepository {
        return TrainingRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideTrainingUseCases(
        repository: TrainingRepository,
        complexRepository: ComplexRepository,
        stringResourcesManager: StringResourcesManager
    ): TrainingUseCases {
        return TrainingUseCases(
            getAppointedTrainingsUseCase = GetAppointedTrainingsUseCase(
                repository,
                stringResourcesManager
            ),
            getTrainingListUseCase = GetTrainingListUseCase(repository, stringResourcesManager),
            getAppointedTrainingByIdUseCase = GetAppointedTrainingByIdUseCase(
                repository,
                complexRepository,
                stringResourcesManager
            ),
            getSortedByOrderTrainingItemInListUseCase = GetSortedByOrderTrainingItemInListUseCase(),
            getTrainingByIdUseCase = GetTrainingByIdUseCase(
                repository,
                complexRepository,
                stringResourcesManager
            )
        )
    }
}