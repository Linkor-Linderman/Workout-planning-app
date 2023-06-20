package com.example.trainingplanapp.featureExercises.domain.useCase.complex

import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureExercises.domain.model.Complex
import com.example.trainingplanapp.featureExercises.domain.model.ExerciseInfo
import com.example.trainingplanapp.featureExercises.domain.repository.ComplexRepository
import com.example.trainingplanapp.featureExercises.domain.repository.ExerciseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class GetComplexByIdUseCase(
    private val repository: ComplexRepository,
    private val exerciseRepository: ExerciseRepository,
    private val stringResourcesManager: StringResourcesManager
) {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)
    private val connectionErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.no_connection_error)

    operator fun invoke(
        complexId: String
    ): Flow<Resource<Complex>> =
        flow {
            emit(Resource.Loading())
            try {
                val complex = repository.getComplexById(complexId)
                emit(Resource.Success(complex.copy(
                    exercisesInformation = complex.exercises.map {
                        val exercise = exerciseRepository.getExerciseById(it.exerciseId)
                        ExerciseInfo(
                            it.exerciseId,
                            exercise.imageId,
                            exercise.muscleGroups,
                            exercise.name
                        )
                    }
                )))
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