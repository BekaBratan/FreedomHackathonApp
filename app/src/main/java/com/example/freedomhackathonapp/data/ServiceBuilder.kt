package com.example.freedomhackathonapp.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val client = OkHttpClient.Builder().build()


    private const val BASE_URL = "http://localhost:8000/search?prompt=" // url

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api by lazy {
        retrofit.create(ApiService::class.java)
    }

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}