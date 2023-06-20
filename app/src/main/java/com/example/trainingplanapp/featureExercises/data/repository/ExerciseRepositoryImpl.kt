package com.example.trainingplanapp.featureExercises.data.repository

import com.example.trainingplanapp.featureExercises.data.remote.ExerciseApi
import com.example.trainingplanapp.featureExercises.data.remote.dto.CreateExerciseDto
import com.example.trainingplanapp.featureExercises.data.remote.dto.DefaultValuesDto
import com.example.trainingplanapp.featureExercises.data.remote.dto.GetExerciseDto
import com.example.trainingplanapp.featureExercises.data.remote.dto.PaginationQueryDto
import com.example.trainingplanapp.featureExercises.domain.model.CreateExercise
import com.example.trainingplanapp.featureExercises.domain.model.Exercise
import com.example.trainingplanapp.featureExercises.domain.model.ExerciseInfo
import com.example.trainingplanapp.featureExercises.domain.model.GetExercise
import com.example.trainingplanapp.featureExercises.domain.repository.ExerciseRepository

class ExerciseRepositoryImpl(
    private val api: ExerciseApi
) : ExerciseRepository {
    override suspend fun createExercise(createExercise: CreateExercise) {
        api.createExercise(
            CreateExerciseDto(
                defaultValues = DefaultValuesDto(
                    createExercise.defaultValues.duration,
                    createExercise.defaultValues.repetitions,
                    createExercise.defaultValues.weight,
                ),
                description = createExercise.description,
                imageId = createExercise.imageId,
                muscleGroups = createExercise.muscleGroups,
                name = createExercise.name,
                published = createExercise.published
            )
        )
    }

    override suspend fun getExerciseById(exerciseId: String): Exercise {
        return api.getExerciseById(exerciseId).toExercise()
    }

    override suspend fun getExercisesList(getExercise: GetExercise): List<ExerciseInfo> {
        return api.getExercisesList(
            GetExerciseDto(
                common = getExercise.common,
                my = getExercise.my,
                shared = getExercise.shared,
                name = getExercise.name,
                paginationQueryDto = PaginationQueryDto(
                    getExercise.paginationQueryDto.page,
                    getExercise.paginationQueryDto.size
                ),
                muscleGroups = getExercise.muscleGroups,
                published = getExercise.published,
                liked = getExercise.liked
            )
        ).data.map { it.toExerciseInfo() }
    }

}