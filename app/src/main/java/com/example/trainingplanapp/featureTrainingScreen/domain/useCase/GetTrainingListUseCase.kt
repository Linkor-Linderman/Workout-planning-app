package com.example.trainingplanapp.featureTrainingScreen.domain.useCase

import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureTrainingScreen.domain.model.QueryTraining
import com.example.trainingplanapp.featureTrainingScreen.domain.model.TrainingInfo
import com.example.trainingplanapp.featureTrainingScreen.domain.repository.TrainingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class GetTrainingListUseCase(
    private val repository: TrainingRepository,
    private val stringResourcesManager: StringResourcesManager
) {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)
    private val connectionErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.no_connection_error)

    operator fun invoke(
        queryTraining: QueryTraining
    ): Flow<Resource<List<TrainingInfo>>> =
        flow {
            emit(Resource.Loading())
            try {
                val trainingInfoList = repository.getTrainings(queryTraining)
                emit(Resource.Success(trainingInfoList))
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
