package com.example.freedomhackathonapp.data

import com.example.freedomhackathonapp.domain.VacancyResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("api/vacancy")
    suspend fun getVacancy(): List<VacancyResponse>
}