package com.example.trainingplanapp.featureAuthorization.domain.useCases

class EmptyFieldsValidationUseCase {
    operator fun invoke(vararg textFields: String?):Boolean {
        for (textField in textFields){
            if (textField.isNullOrBlank())
                return false
        }
        return true
    }

}