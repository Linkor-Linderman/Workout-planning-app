package com.example.trainingplanapp.featureTrainingScreen.domain.useCase

import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureExercises.domain.repository.ComplexRepository
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.ComplexInTrainExtended
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.TrainingWithExtendedComplexInfo
import com.example.trainingplanapp.featureTrainingScreen.domain.repository.TrainingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class GetAppointedTrainingByIdUseCase(
    private val repository: TrainingRepository,
    private val complexRepository: ComplexRepository,
    private val stringResourcesManager: StringResourcesManager
) {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)
    private val connectionErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.no_connection_error)

    operator fun invoke(
        trainingId: String
    ): Flow<Resource<TrainingWithExtendedComplexInfo>> =
        flow {
            emit(Resource.Loading())
            try {
                val training = repository.getAppointedTrainingById(trainingId)
                val trainingWithExtendedComplexInfo = TrainingWithExtendedComplexInfo(
                    common = training.common,
                    complexes = training.complexes.map {
                        val complex = complexRepository.getComplexById(it.complexId)
                        ComplexInTrainExtended(
                            it.complexId,
                            name = complex.name,
                            description = complex.description,
                            exerciseValues = it.exerciseValues,
                            orderNumber = it.orderNumber,
                        )
                    },
                    description = training.description,
                    exercises = training.exercises,
                    id = training.id,
                    name = training.name,
                    published = training.published
                )
                emit(Resource.Success(trainingWithExtendedComplexInfo))
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