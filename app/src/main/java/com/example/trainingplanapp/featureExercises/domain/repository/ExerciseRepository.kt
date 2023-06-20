package com.example.trainingplanapp.featureExercises.domain.repository

import com.example.trainingplanapp.featureExercises.domain.model.CreateExercise
import com.example.trainingplanapp.featureExercises.domain.model.Exercise
import com.example.trainingplanapp.featureExercises.domain.model.ExerciseInfo
import com.example.trainingplanapp.featureExercises.domain.model.GetExercise

interface ExerciseRepository {
    suspend fun createExercise(
        createExercise: CreateExercise
    )

    suspend fun getExerciseById(
        exerciseId: String
    ): Exercise

    suspend fun getExercisesList(
        getExercise: GetExercise
    ): List<ExerciseInfo>
}