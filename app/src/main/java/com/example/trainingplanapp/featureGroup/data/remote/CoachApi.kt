package com.example.trainingplanapp.featureGroup.data.remote

import com.example.trainingplanapp.featureGroup.data.remote.dto.CoachInfoListDTO
import com.example.trainingplanapp.featureGroup.data.remote.dto.PaginationQueryDto
import com.example.trainingplanapp.featureGroup.data.remote.dto.RequestsListDTO
import com.example.trainingplanapp.featureGroup.data.remote.dto.UserInfoListDTO
import retrofit2.http.*

interface CoachApi {
    @POST("/trainer/")
    suspend fun getCoaches(
        @Query("shortName") shortName: String,
        @Body paginationQueryDto: PaginationQueryDto
    ): CoachInfoListDTO

    @POST("/trainer/{trainerId}")
    suspend fun addToCoach(
        @Path("trainerId") trainerId: String,
    )

    @PUT("/trainer/query/{queryId}")
    suspend fun acceptRequest(
        @Path("queryId") queryId: String,
    )

    @DELETE("/trainer/query/{queryId}")
    suspend fun rejectRequest(
        @Path("queryId") queryId: String,
    )

    @POST("/trainer/query/my/")
    suspend fun getRequests(
        @Query("name") name: String,
        @Body paginationQueryDto: PaginationQueryDto
    ): RequestsListDTO

    @POST("/trainer/users")
    suspend fun getUsers(
        @Query("name") name: String,
        @Body paginationQueryDto: PaginationQueryDto
    ): UserInfoListDTO

    @POST("/user/promote/")
    suspend fun becomeCoach(
        @Query("shortName") shortName: String,
    )
}