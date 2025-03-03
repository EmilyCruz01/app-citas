package com.example.app_citas.dates.domain

import com.example.app_citas.dates.data.model.Date
import com.example.app_citas.dates.data.repository.DateRepository

class GetDatesUseCase {
    private val repository = DateRepository()

    suspend operator fun invoke(userId: Int): Result<List<Date>> {
        val result = repository.getDates(userId)
        return result
    }
}