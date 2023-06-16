package com.example.trainingplanapp.featureGroup.domain.useCase

data class GroupUseCases(
    val getTrainingGroupUseCase: GetTrainingGroupUseCase,
    val getMyGroupsUseCase: GetMyGroupsUseCase,
    val getGroupInfoById: GetGroupInfoById,
    val createGroupUseCase: CreateGroupUseCase,
    val editGroupUseCase: EditGroupUseCase,
    val deleteGroupByIdUseCase: DeleteGroupByIdUseCase
)
