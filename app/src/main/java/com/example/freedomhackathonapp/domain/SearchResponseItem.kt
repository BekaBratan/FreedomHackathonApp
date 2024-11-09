package com.example.freedomhackathonapp.domain

data class SearchResponseItem(
    val birth_date: String,
    val conformity_assessment: String,
    val country: String,
    val education: String,
    val experience: String,
    val full_name: String,
    val high_skills: String,
    val percent_appropriate: String,
    val resume_link: String
)