package com.example.app_citas.core.network

import com.example.app_citas.login.data.datasource.LoginService
import com.example.app_citas.register.data.datasource.RegisterService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "http://3.229.240.157:3000/"

//    val logging = HttpLoggingInterceptor().apply {
//        level = HttpLoggingInterceptor.Level.BODY
//    }

//    val client = OkHttpClient.Builder()
//        .addInterceptor(logging)
//        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
            .build()
    }

    val loginService: LoginService by lazy {
        retrofit.create(LoginService::class.java)
    }

    val registerService: RegisterService by lazy {
        retrofit.create(RegisterService::class.java)
    }


}