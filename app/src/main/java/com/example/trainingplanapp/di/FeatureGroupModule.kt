package com.example.trainingplanapp.di

import com.example.trainingplanapp.di.utill.StringResourcesManager
import com.example.trainingplanapp.featureGroup.data.remote.CoachApi
import com.example.trainingplanapp.featureGroup.data.remote.GroupApi
import com.example.trainingplanapp.featureGroup.data.repository.CoachRepositoryImpl
import com.example.trainingplanapp.featureGroup.data.repository.GroupRepositoryImpl
import com.example.trainingplanapp.featureGroup.domain.repository.CoachRepository
import com.example.trainingplanapp.featureGroup.domain.repository.GroupRepository
import com.example.trainingplanapp.featureGroup.domain.useCase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeatureGroupModule {
    @Provides
    @Singleton
    fun provideGroupApi(
        retrofit: Retrofit
    ): GroupApi {
        return retrofit.create(GroupApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGroupRepository(
        api: GroupApi,
    ): GroupRepository {
        return GroupRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGroupUseCases(
        repository: GroupRepository,
        stringResourcesManager: StringResourcesManager
    ): GroupUseCases {
        return GroupUseCases(
            getMyGroupsUseCase = GetMyGroupsUseCase(repository, stringResourcesManager),
            getTrainingGroupUseCase = GetTrainingGroupUseCase(repository, stringResourcesManager),
            getGroupInfoById = GetGroupInfoById(repository, stringResourcesManager),
            createGroupUseCase = CreateGroupUseCase(repository, stringResourcesManager),
            editGroupUseCase = EditGroupUseCase(repository, stringResourcesManager),
            deleteGroupByIdUseCase = DeleteGroupByIdUseCase(repository, stringResourcesManager)
        )
    }

    @Provides
    @Singleton
    fun provideCoachApi(
        retrofit: Retrofit
    ): CoachApi {
        return retrofit.create(CoachApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoachRepository(
        api: CoachApi,
    ): CoachRepository {
        return CoachRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCoachUseCases(
        repository: CoachRepository,
        stringResourcesManager: StringResourcesManager
    ): CoachUseCases {
        return CoachUseCases(
            getCoachListUseCase = GetCoachListUseCase(repository, stringResourcesManager),
            becomeCoachUseCase = BecomeCoachUseCase(repository, stringResourcesManager),
            requestsUseCase = GetRequestsUseCase(repository, stringResourcesManager),
            addToCoachUseCase = AddToCoachUseCase(repository, stringResourcesManager),
            acceptRequestUseCase = AcceptRequestUseCase(repository, stringResourcesManager),
            dismissRequestUseCase = DismissRequestUseCase(repository, stringResourcesManager),
            getUsersUseCase = GetUsersUseCase(repository, stringResourcesManager)
        )
    }

}