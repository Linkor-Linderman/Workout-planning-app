package com.example.trainingplanapp.featureGroup.data.repository

import com.example.trainingplanapp.featureGroup.data.remote.CoachApi
import com.example.trainingplanapp.featureGroup.data.remote.dto.PaginationQueryDto
import com.example.trainingplanapp.featureGroup.domain.model.CoachInfo
import com.example.trainingplanapp.featureGroup.domain.model.RequestInfo
import com.example.trainingplanapp.featureGroup.domain.model.UserInfo
import com.example.trainingplanapp.featureGroup.domain.repository.CoachRepository

class CoachRepositoryImpl(
    private val api: CoachApi
) : CoachRepository {
    override suspend fun getCoaches(shortName: String, page: Int, size: Int): List<CoachInfo> {
        return api.getCoaches(shortName, PaginationQueryDto(page, size)).data.map { it.toCoachInfo() }
    }

    override suspend fun addToCoach(trainerId: String) {
        api.addToCoach(trainerId)
    }

    override suspend fun acceptRequest(queryId: String) {
        api.acceptRequest(queryId)
    }

    override suspend fun rejectRequest(queryId: String) {
        api.rejectRequest(queryId)
    }

    override suspend fun getRequests(name: String, page: Int, size: Int): List<RequestInfo> {
        return api.getRequests(name, PaginationQueryDto(page, size)).data.map { it.toRequestInfo() }
    }

    override suspend fun getUsers(name: String, page: Int, size: Int): List<UserInfo> {
        return api.getUsers(name, PaginationQueryDto(page, size)).data.map { it.toUserInfo() }
    }

    override suspend fun becomeCoach(shortName: String) {
        api.becomeCoach(shortName)
    }
}