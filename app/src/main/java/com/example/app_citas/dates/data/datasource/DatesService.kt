package com.example.app_citas.dates.data.datasource

import com.example.app_citas.dates.data.model.Date
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DatesService {
    @GET("dates/{userId}")
    suspend fun getDates(@Path("userId") userId: Int): Response<List<Date>>
}