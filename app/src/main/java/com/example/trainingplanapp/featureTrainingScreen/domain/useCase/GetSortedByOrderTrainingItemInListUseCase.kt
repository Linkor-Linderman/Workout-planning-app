package com.example.trainingplanapp.featureTrainingScreen.domain.useCase

import com.example.trainingplanapp.featureTrainingScreen.domain.model.trainingInfo.*

class GetSortedByOrderTrainingItemInListUseCase {

    operator fun invoke(training: TrainingWithExtendedComplexInfo): List<TrainingListItem> {
        val listOfComplex = training.complexes
        val listOfExercises = training.exercises

        val combinedList: List<TrainingListItem> = listOfExercises + listOfComplex

        val sortedList: List<TrainingListItem> = combinedList.sortedWith(compareBy { item ->
            when (item) {
                is ExerciseInTrain -> item.orderNumber
                is ComplexInTrainExtended -> item.orderNumber
                else -> throw IllegalArgumentException("Unsupported type")
            }
        })
        return sortedList
    }
}