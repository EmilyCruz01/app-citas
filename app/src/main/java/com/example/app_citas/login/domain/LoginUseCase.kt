package com.example.app_citas.login.domain

import com.example.app_citas.login.data.model.LoginRequest
import com.example.app_citas.login.data.model.LoginResponse
import com.example.app_citas.login.data.repository.LoginRepository

class LoginUseCase {
    private val repository = LoginRepository ()

    suspend operator fun invoke(loginRequest: LoginRequest): Result<LoginResponse> {
        val result = repository.login(loginRequest)
        return result
    }
}