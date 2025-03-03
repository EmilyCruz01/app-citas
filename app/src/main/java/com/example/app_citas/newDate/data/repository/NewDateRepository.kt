package com.example.app_citas.newDate.data.repository
import com.example.app_citas.core.network.RetrofitHelper
import com.example.app_citas.newDate.data.model.NewDateRequest
import com.example.app_citas.newDate.data.model.NewDateResponse

class NewDateRepository {
    private val newDateService = RetrofitHelper.newDateService

    suspend fun newDate(request : NewDateRequest): Result<NewDateResponse> {
        return try {
            val response = newDateService.newDate(request)

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