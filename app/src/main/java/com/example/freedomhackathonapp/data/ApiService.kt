package com.example.freedomhackathonapp.data

import com.example.freedomhackathonapp.domain.HelloWorldResponse
import com.example.freedomhackathonapp.domain.NewSearchResponse
import com.example.freedomhackathonapp.domain.SearchResponse
import com.example.freedomhackathonapp.domain.VacancyResponse
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("/api/vacancy")
    suspend fun getVacancy(): List<VacancyResponse>

    @GET("/")
    suspend fun getEmpty(): HelloWorldResponse

    @GET("/search")
    suspend fun search(
        @Query("vacancy") vacancy: String
    ): NewSearchResponse
}