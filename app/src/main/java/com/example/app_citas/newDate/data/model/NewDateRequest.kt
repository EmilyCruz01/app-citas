package com.example.app_citas.newDate.data.model


data class NewDateRequest (
    val user_id: Int,
    val title: String,
    val description: String,
    val date: Long
)