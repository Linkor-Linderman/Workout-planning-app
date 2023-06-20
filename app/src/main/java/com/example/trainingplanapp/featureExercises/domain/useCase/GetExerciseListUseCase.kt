package com.example.trainingplanapp.featureExercises.domain.useCase

import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureExercises.domain.model.ExerciseInfo
import com.example.trainingplanapp.featureExercises.domain.model.GetExercise
import com.example.trainingplanapp.featureExercises.domain.repository.ExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class GetExerciseListUseCase(
    private val repository: ExerciseRepository,
    private val stringResourcesManager: StringResourcesManager
) {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)
    private val connectionErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.no_connection_error)

    operator fun invoke(
        getExercise: GetExercise
    ): Flow<Resource<List<ExerciseInfo>>> =
        flow {
            emit(Resource.Loading())
            try {
                val exerciseInfoList = repository.getExercisesList(getExercise)
                emit(Resource.Success(exerciseInfoList))
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