package com.example.app_citas.newDate.data.datasource

import com.example.app_citas.newDate.data.model.NewDateRequest
import com.example.app_citas.newDate.data.model.NewDateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NewDateService {
    @POST("dates")
    suspend fun newDate(@Body request: NewDateRequest): Response<NewDateResponse>
}