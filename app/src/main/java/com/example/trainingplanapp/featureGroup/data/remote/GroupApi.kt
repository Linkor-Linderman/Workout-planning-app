package com.example.trainingplanapp.featureGroup.data.remote

import com.example.trainingplanapp.featureGroup.data.remote.dto.*
import retrofit2.http.*

interface GroupApi {
    @GET("/my/groups/")
    suspend fun getMyGroups(@Query("name") name: String): List<GroupInfoDTO>

    @GET("/training/groups/")
    suspend fun getTrainingGroups(@Query("name") name: String): List<GroupInfoDTO>


    @GET("/group/{groupId}/users")
    suspend fun getUsersInGroupById(@Path("groupId") groupId: String): List<UserInfoDTO>

    @POST("/group/{groupId}/users")
    suspend fun addUserIntoGroup(
        @Path("groupId") groupId: String,
        @Body idsArrayDTO: IdsArrayDTO
    )

    @DELETE("/group/{groupId}/users")
    suspend fun deleteUserFromGroup(
        @Path("groupId") groupId: String,
        @Body idsArrayDTO: IdsArrayDTO
    )

    @GET("/group/")
    suspend fun createGroup(
        @Query("imageId") imageId: String,
        @Query("name") name: String,
    )

    @POST("/groupfull")
    suspend fun createFullGroup(
        @Body groupCreateDto: GroupCreateDto
    )

    @GET("/group/{groupId}")
    suspend fun getGroup(
        @Path("groupId") groupId: String
    ): GroupDTO

    @PUT("/groupfull/{groupId}")
    suspend fun editGroup(
        @Path("groupId") groupId: String,
        @Body groupCreateDto: GroupCreateDto
    )

    @DELETE("/group/{groupId}")
    suspend fun deleteGroup(
        @Path("groupId") groupId: String,
    )
}