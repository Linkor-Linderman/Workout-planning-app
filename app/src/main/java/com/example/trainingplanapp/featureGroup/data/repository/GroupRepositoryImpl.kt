package com.example.trainingplanapp.featureGroup.data.repository

import android.util.Log
import com.example.trainingplanapp.featureGroup.data.remote.GroupApi
import com.example.trainingplanapp.featureGroup.data.remote.dto.GroupCreateDto
import com.example.trainingplanapp.featureGroup.data.remote.dto.IdsArrayDTO
import com.example.trainingplanapp.featureGroup.domain.model.Group
import com.example.trainingplanapp.featureGroup.domain.model.GroupCreate
import com.example.trainingplanapp.featureGroup.domain.model.GroupInfo
import com.example.trainingplanapp.featureGroup.domain.model.UserInfo
import com.example.trainingplanapp.featureGroup.domain.repository.GroupRepository

class GroupRepositoryImpl(
    private val api: GroupApi
) : GroupRepository {
    override suspend fun getMyGroups(name: String): List<GroupInfo> {
        val list = api.getMyGroups(name)
        Log.d("SOME", list.toString())
        if (list.isEmpty())
            return emptyList()
        return api.getMyGroups(name).map { it.toGroupInfo() }
    }

    override suspend fun getTrainingGroups(name: String): List<GroupInfo> {
        return api.getTrainingGroups(name).map { it.toGroupInfo() }
    }

    override suspend fun getUsersInGroupById(groupId: String): List<UserInfo> {
        return api.getUsersInGroupById(groupId).map { it.toUserInfo() }
    }

    override suspend fun addUserIntoGroup(groupId: String, idsArray: List<String>) {
        api.addUserIntoGroup(groupId, IdsArrayDTO(idsArray))
    }

    override suspend fun deleteUserFromGroup(groupId: String, idsArray: List<String>) {
        api.deleteUserFromGroup(groupId, IdsArrayDTO(idsArray))
    }

    override suspend fun createGroup(imageId: String, name: String) {
        api.createGroup(imageId, name)
    }

    override suspend fun createFullGroup(groupCreate: GroupCreate) {
        api.createFullGroup(
            GroupCreateDto(
                groupCreate.description,
                groupCreate.name,
                groupCreate.trainers,
                groupCreate.users
            )
        )
    }

    override suspend fun getGroup(groupId: String): Group {
        return api.getGroup(groupId).toGroup()
    }

    override suspend fun editGroup(groupId: String, groupCreate: GroupCreate) {
        api.editGroup(
            groupId,
            GroupCreateDto(
                groupCreate.description,
                groupCreate.name,
                groupCreate.trainers,
                groupCreate.users
            )
        )
    }

    override suspend fun deleteGroup(groupId: String) {
        api.deleteGroup(groupId)
    }
}