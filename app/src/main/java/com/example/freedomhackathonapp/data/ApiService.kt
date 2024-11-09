package com.example.freedomhackathonapp.data

import com.example.freedomhackathonapp.domain.SearchResponse
import com.example.freedomhackathonapp.domain.VacancyResponse
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("api/vacancy")
    suspend fun getVacancy(): List<VacancyResponse>

    @GET("search")
    suspend fun search(@Query("prompt") prompt: String): SearchResponse
}