package com.example.app_citas.login.data.datasource

import com.example.app_citas.login.data.model.LoginRequest
import com.example.app_citas.login.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService{
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}