package com.example.app_citas.dates.data.repository

import com.example.app_citas.core.network.RetrofitHelper
import com.example.app_citas.dates.data.model.Date

class DateRepository {
    private val datesService = RetrofitHelper.datesService

    suspend fun getDates(userId: Int): Result<List<Date>> {
        return try {
            val response = datesService.getDates(userId = userId)

            if(response.isSuccessful){
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}