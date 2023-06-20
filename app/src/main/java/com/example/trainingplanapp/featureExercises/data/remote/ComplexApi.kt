package com.example.trainingplanapp.featureExercises.data.remote

import com.example.trainingplanapp.featureExercises.data.remote.dto.*
import retrofit2.http.*

interface ComplexApi {
    @POST("/complex/")
    suspend fun createComplex(
        @Body complexCreateDto: ComplexCreateDto
    )

    @GET("/complex/{complexId}")
    suspend fun getComplexById(
        @Path("complexId") complexId: String
    ): ComplexDto

    @PUT("/complex/{complexId}")
    suspend fun editComplexById(
        @Path("complexId") complexId: String,
        @Body complexCreateDto: ComplexCreateDto
    ): ComplexDto

    @POST("/complexes/")
    suspend fun getComplexList(
        @Body getComplexDto: GetComplexDto
    ): ComplexListDto
}