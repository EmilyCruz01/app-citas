package com.example.app_citas.register.domain

import com.example.app_citas.login.data.model.LoginRequest
import com.example.app_citas.login.data.model.LoginResponse
import com.example.app_citas.login.data.repository.LoginRepository
import com.example.app_citas.register.data.model.RegisterRequest
import com.example.app_citas.register.data.model.RegisterResponse
import com.example.app_citas.register.data.repository.RegisterRepository

class RegisterUseCase {
    private val repository = RegisterRepository()

    suspend operator fun invoke(registerRequest: RegisterRequest): Result<RegisterResponse> {
        val result = repository.register(registerRequest)
        return result
    }
}