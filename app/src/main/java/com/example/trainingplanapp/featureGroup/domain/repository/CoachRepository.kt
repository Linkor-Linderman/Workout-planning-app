package com.example.trainingplanapp.featureGroup.domain.repository

import com.example.trainingplanapp.featureGroup.domain.model.CoachInfo
import com.example.trainingplanapp.featureGroup.domain.model.RequestInfo
import com.example.trainingplanapp.featureGroup.domain.model.UserInfo

interface CoachRepository {
    suspend fun getCoaches(
        shortName: String,
        page: Int,
        size: Int
    ): List<CoachInfo>

    suspend fun addToCoach(
        trainerId: String,
    )

    suspend fun acceptRequest(
        queryId: String,
    )

    suspend fun rejectRequest(
        queryId: String,
    )

    suspend fun getRequests(
        name: String,
        page: Int,
        size: Int
    ): List<RequestInfo>

    suspend fun getUsers(
        name: String,
        page: Int,
        size: Int
    ): List<UserInfo>

    suspend fun becomeCoach(
        shortName: String,
    )
}