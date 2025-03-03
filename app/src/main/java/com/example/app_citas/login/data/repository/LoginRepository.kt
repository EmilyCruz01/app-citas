package com.example.app_citas.login.data.repository

import com.example.app_citas.core.network.RetrofitHelper
import com.example.app_citas.login.data.model.LoginRequest
import com.example.app_citas.login.data.model.LoginResponse

class LoginRepository {
    private val loginService = RetrofitHelper.loginService

    suspend fun login (request : LoginRequest): Result<LoginResponse> {
        return try {
            val response = loginService.login(request)

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