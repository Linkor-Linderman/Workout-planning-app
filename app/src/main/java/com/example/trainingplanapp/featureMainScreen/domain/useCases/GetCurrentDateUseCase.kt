package com.example.trainingplanapp.featureMainScreen.domain.useCases

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class GetCurrentDateUseCase {
    operator fun invoke(): String {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM", Locale.ENGLISH)
        return today.format(formatter)
    }
}