package com.example.trainingplanapp.featureTrainingScreen.domain.repository

import com.example.trainingplanapp.featureTrainingScreen.domain.model.AppointedTraining
import com.example.trainingplanapp.featureTrainingScreen.domain.model.QueryTraining
import com.example.trainingplanapp.featureTrainingScreen.domain.model.TrainingInfo
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.Training

interface TrainingRepository {

    suspend fun getTrainings(
        queryTraining: QueryTraining
    ): List<TrainingInfo>

    suspend fun getMyAppointedTrains(): List<AppointedTraining>

    suspend fun getTrainingById(
        trainingId: String
    ): Training

    suspend fun getAppointedTrainingById(
        trainingId: String
    ): Training
}