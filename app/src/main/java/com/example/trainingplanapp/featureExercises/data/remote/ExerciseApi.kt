package com.example.trainingplanapp.featureExercises.data.remote

import com.example.trainingplanapp.featureExercises.data.remote.dto.CreateExerciseDto
import com.example.trainingplanapp.featureExercises.data.remote.dto.ExerciseDto
import com.example.trainingplanapp.featureExercises.data.remote.dto.ExerciseListDto
import com.example.trainingplanapp.featureExercises.data.remote.dto.GetExerciseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ExerciseApi {
    @POST("/exercise/")
    suspend fun createExercise(
        @Body createExerciseDto: CreateExerciseDto
    )

    @GET("/exercise/{exerciseId}")
    suspend fun getExerciseById(
        @Path("exerciseId") exerciseId: String
    ): ExerciseDto

    @POST("/exercises/")
    suspend fun getExercisesList(
        @Body getExerciseDto: GetExerciseDto
    ): ExerciseListDto
}