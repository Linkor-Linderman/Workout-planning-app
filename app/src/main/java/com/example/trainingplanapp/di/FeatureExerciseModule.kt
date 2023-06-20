package com.example.trainingplanapp.di

import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureExercises.data.remote.ComplexApi
import com.example.trainingplanapp.featureExercises.data.remote.ExerciseApi
import com.example.trainingplanapp.featureExercises.data.repository.ComplexRepositoryImpl
import com.example.trainingplanapp.featureExercises.data.repository.ExerciseRepositoryImpl
import com.example.trainingplanapp.featureExercises.domain.repository.ComplexRepository
import com.example.trainingplanapp.featureExercises.domain.repository.ExerciseRepository
import com.example.trainingplanapp.featureExercises.domain.useCase.CreateExerciseUseCase
import com.example.trainingplanapp.featureExercises.domain.useCase.ExerciseUseCases
import com.example.trainingplanapp.featureExercises.domain.useCase.GetExerciseByIdUseCase
import com.example.trainingplanapp.featureExercises.domain.useCase.GetExerciseListUseCase
import com.example.trainingplanapp.featureExercises.domain.useCase.complex.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FeatureExerciseModule {
    @Provides
    @Singleton
    fun provideExerciseApi(
        retrofit: Retrofit
    ): ExerciseApi {
        return retrofit.create(ExerciseApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(
        api: ExerciseApi,
    ): ExerciseRepository {
        return ExerciseRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideExerciseUseCases(
        repository: ExerciseRepository,
        stringResourcesManager: StringResourcesManager
    ): ExerciseUseCases {
        return ExerciseUseCases(
            getExerciseByIdUseCase = GetExerciseByIdUseCase(repository, stringResourcesManager),
            getExerciseListUseCase = GetExerciseListUseCase(repository, stringResourcesManager),
            createExerciseUseCase = CreateExerciseUseCase(repository, stringResourcesManager)
        )
    }

    @Provides
    @Singleton
    fun provideComplexApi(
        retrofit: Retrofit
    ): ComplexApi {
        return retrofit.create(ComplexApi::class.java)
    }

    @Provides
    @Singleton
    fun provideComplexRepository(
        api: ComplexApi,
    ): ComplexRepository {
        return ComplexRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideComplexUseCases(
        repository: ComplexRepository,
        exerciseRepository: ExerciseRepository,
        stringResourcesManager: StringResourcesManager
    ): ComplexUseCases {
        return ComplexUseCases(
            getComplexByIdUseCase = GetComplexByIdUseCase(repository, exerciseRepository, stringResourcesManager),
            getComplexListUseCase = GetComplexListUseCase(repository, stringResourcesManager),
            createComplexUseCase = CreateComplexUseCase(repository, stringResourcesManager),
            editComplexUseCase = EditComplexUseCase(repository, stringResourcesManager)
        )
    }
}