package com.example.trainingplanapp.featureTrainingScreen.data.remote

import com.example.trainingplanapp.featureTrainingScreen.data.remote.dto.AppointedTrainingDto
import com.example.trainingplanapp.featureTrainingScreen.data.remote.dto.QueryTrainingDto
import com.example.trainingplanapp.featureTrainingScreen.data.remote.dto.TrainingsListDto
import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.Training
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TrainingApi {
    @POST("/trainings/")
    suspend fun getTrainings(
        @Body queryTrainingDto: QueryTrainingDto
    ): TrainingsListDto

    @GET("/training/appointed/")
    suspend fun getMyAppointedTrains(): List<AppointedTrainingDto>

    @GET("/training/{trainingId}")
    suspend fun getTrainingById(
        @Path("trainingId") trainingId: String
    ): Training

    @GET("/training/appointed/{id}")
    suspend fun getAppointedTrainingById(
        @Path("id") trainingId: String
    ): Training
}