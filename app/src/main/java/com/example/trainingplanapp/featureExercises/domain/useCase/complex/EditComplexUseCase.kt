package com.example.trainingplanapp.featureExercises.domain.useCase.complex

import com.example.trainingplanapp.R
import com.example.trainingplanapp.common.Resource
import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureExercises.domain.model.ComplexCreate
import com.example.trainingplanapp.featureExercises.domain.repository.ComplexRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class EditComplexUseCase(
    private val repository: ComplexRepository,
    private val stringResourcesManager: StringResourcesManager
) {
    private val unexpectedErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.unexpected_error)
    private val connectionErrorMessage =
        stringResourcesManager.getStringResourceById(R.string.no_connection_error)

    operator fun invoke(
        complexId: String,
        complexCreate: ComplexCreate
    ): Flow<Resource<Unit>> =
        flow {
            emit(Resource.Loading())
            try {
                repository.editComplexById(
                    complexId,
                    complexCreate
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
}