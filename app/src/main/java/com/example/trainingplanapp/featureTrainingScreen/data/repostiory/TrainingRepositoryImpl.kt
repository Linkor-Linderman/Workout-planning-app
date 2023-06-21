package com.example.trainingplanapp.featureTrainingScreen.data.repostiory

import com.example.trainingplanapp.featureTrainingScreen.data.remote.TrainingApi
import com.example.trainingplanapp.featureTrainingScreen.data.remote.dto.PaginationQueryDto
import com.example.trainingplanapp.featureTrainingScreen.data.remote.dto.QueryTrainingDto
import com.example.trainingplanapp.featureTrainingScreen.domain.model.AppointedTraining
import com.example.trainingplanapp.featureTrainingScreen.domain.model.QueryTraining
import com.example.trainingplanapp.featureTrainingScreen.domain.model.TrainingInfo
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.Training
import com.example.trainingplanapp.featureTrainingScreen.domain.repository.TrainingRepository

class TrainingRepositoryImpl(
    private val api: TrainingApi
) : TrainingRepository {
    override suspend fun getTrainings(queryTraining: QueryTraining): List<TrainingInfo> {
        return api.getTrainings(
            QueryTrainingDto(
                common = queryTraining.common,
                liked = queryTraining.liked,
                my = queryTraining.my,
                name = queryTraining.name,
                paginationQueryDto = PaginationQueryDto(
                    queryTraining.paginationQueryDto.page,
                    queryTraining.paginationQueryDto.size
                ),
                published = queryTraining.published,
                shared = queryTraining.shared
            )
        ).data.map { it.toTrainingInfo() }
    }

    override suspend fun getMyAppointedTrains(): List<AppointedTraining> {
        return api.getMyAppointedTrains().map { it.toAppointedTraining() }
    }

    override suspend fun getTrainingById(trainingId: String): Training {
        return api.getTrainingById(trainingId)
    }

    override suspend fun getAppointedTrainingById(trainingId: String): Training {
        return api.getAppointedTrainingById(trainingId)
    }
}