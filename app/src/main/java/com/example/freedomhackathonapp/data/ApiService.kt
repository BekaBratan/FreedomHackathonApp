package com.example.freedomhackathonapp.data

import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("api/products")
    suspend fun getProducts(): List<String>
}