package com.example.trainingplanapp.featureExercises.domain.repository

import com.example.trainingplanapp.featureExercises.domain.model.Complex
import com.example.trainingplanapp.featureExercises.domain.model.ComplexCreate
import com.example.trainingplanapp.featureExercises.domain.model.ComplexInfo
import com.example.trainingplanapp.featureExercises.domain.model.GetComplex

interface ComplexRepository {
    suspend fun createComplex(
        complexCreate: ComplexCreate
    )

    suspend fun getComplexById(
        complexId: String
    ): Complex

    suspend fun editComplexById(
        complexId: String,
        complexCreate: ComplexCreate
    ): Complex

    suspend fun getComplexList(
        getComplex: GetComplex
    ): List<ComplexInfo>
}