package com.example.app_citas.newDate.domain

import com.example.app_citas.newDate.data.repository.NewDateRepository
import com.example.app_citas.newDate.data.model.NewDateRequest
import com.example.app_citas.newDate.data.model.NewDateResponse

class NewDateUseCase {
    private val repository = NewDateRepository()

    suspend operator fun invoke(newDateRequest: NewDateRequest): Result<NewDateResponse> {
        val result = repository.newDate(newDateRequest)
        return result
    }
}