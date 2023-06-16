package com.example.trainingplanapp.featureGroup.domain.repository

import com.example.trainingplanapp.featureGroup.domain.model.Group
import com.example.trainingplanapp.featureGroup.domain.model.GroupCreate
import com.example.trainingplanapp.featureGroup.domain.model.GroupInfo
import com.example.trainingplanapp.featureGroup.domain.model.UserInfo

interface GroupRepository {
    suspend fun getMyGroups(name: String): List<GroupInfo>

    suspend fun getTrainingGroups(name: String): List<GroupInfo>

    suspend fun getUsersInGroupById(groupId: String): List<UserInfo>

    suspend fun addUserIntoGroup(
        groupId: String,
        idsArray: List<String>
    )

    suspend fun deleteUserFromGroup(
        groupId: String,
        idsArray: List<String>
    )

    suspend fun createGroup(
        imageId: String,
        name: String,
    )

    suspend fun createFullGroup(
        groupCreate: GroupCreate
    )

    suspend fun getGroup(
        groupId: String
    ): Group

    suspend fun editGroup(
        groupId: String,
        groupCreate: GroupCreate
    )
    suspend fun deleteGroup(
        groupId: String,
    )
}