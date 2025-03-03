package com.example.app_citas.register.data.repository

import com.example.app_citas.core.network.RetrofitHelper
import com.example.app_citas.register.data.model.RegisterRequest
import com.example.app_citas.register.data.model.RegisterResponse

class RegisterRepository {
    private val registerService = RetrofitHelper.registerService

    suspend fun register(registerRequest: RegisterRequest): Result<RegisterResponse> {
        return try {
            val response = registerService.register(registerRequest)

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