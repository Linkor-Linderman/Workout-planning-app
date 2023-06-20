package com.example.trainingplanapp.featureExercises.data.repository

import com.example.trainingplanapp.featureExercises.data.remote.ComplexApi
import com.example.trainingplanapp.featureExercises.data.remote.dto.*
import com.example.trainingplanapp.featureExercises.domain.model.Complex
import com.example.trainingplanapp.featureExercises.domain.model.ComplexCreate
import com.example.trainingplanapp.featureExercises.domain.model.ComplexInfo
import com.example.trainingplanapp.featureExercises.domain.model.GetComplex
import com.example.trainingplanapp.featureExercises.domain.repository.ComplexRepository

class ComplexRepositoryImpl(
    private val api: ComplexApi
) : ComplexRepository {
    override suspend fun createComplex(complexCreate: ComplexCreate) {
        api.createComplex(
            ComplexCreateDto(
                complexCreate.complexType,
                complexCreate.description,
                complexCreate.exercises.map {
                    ExerciseBriefInformDto(
                        it.exerciseId,
                        DefaultValuesDto(
                            it.exerciseValues.duration,
                            it.exerciseValues.repetitions,
                            it.exerciseValues.weight,
                        )
                    )
                },
                complexCreate.name,
                complexCreate.published,
                complexCreate.repetitions,
                complexCreate.spaceDuration
            )
        )
    }

    override suspend fun getComplexById(complexId: String): Complex {
        return api.getComplexById(complexId).toComplex()
    }

    override suspend fun editComplexById(
        complexId: String,
        complexCreate: ComplexCreate
    ): Complex {
        return api.editComplexById(
            complexId,
            ComplexCreateDto(
                complexCreate.complexType,
                complexCreate.description,
                complexCreate.exercises.map {
                    ExerciseBriefInformDto(
                        it.exerciseId,
                        DefaultValuesDto(
                            it.exerciseValues.duration,
                            it.exerciseValues.repetitions,
                            it.exerciseValues.weight,
                        )
                    )
                },
                complexCreate.name,
                complexCreate.published,
                complexCreate.repetitions,
                complexCreate.spaceDuration
            )
        ).toComplex()
    }

    override suspend fun getComplexList(
        getComplex: GetComplex
    ): List<ComplexInfo> {
        return api.getComplexList(
            GetComplexDto(
                common = getComplex.common,
                liked = getComplex.liked,
                my = getComplex.my,
                name = getComplex.name,
                published = getComplex.published,
                shared = getComplex.shared,
                paginationQueryDto = PaginationQueryDto(
                    getComplex.paginationQueryDto.page,
                    getComplex.paginationQueryDto.size
                )
            )
        ).data.map { it.toComplexInfo() }
    }
}