package com.example.app_citas.login.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    val success: Boolean,
    val user_id: Int,
    val message: String
)