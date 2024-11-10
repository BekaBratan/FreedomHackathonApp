package com.example.freedomhackathonapp.domain


import com.google.gson.annotations.SerializedName

data class NewSearchResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("total")
    val total: Int // 1
) {
    data class Data(
        @SerializedName("birth_date")
        val birthDate: String, // 28-09-1993, 31 год
        @SerializedName("conformity_assessment")
        val conformityAssessment: String, // Подходит
        @SerializedName("country")
        val country: String, // Казахстан, Актобе
        @SerializedName("education")
        val education: String, // Среднее специальное образование
        @SerializedName("experience")
        val experience: String, // Опыт работы Backend-разработчиком более 1 года. Разработка бэкенда для онлайн-чата с использованием Go и PostgreSQL.  Опыт работы с WebSocket, системой хранения истории сообщений, проектированием архитектуры, интеграцией с базой данных, обеспечением производительности сервиса. Разработка серверной части игры StarQ.
        @SerializedName("full_name")
        val fullName: String, // Пурханов Нурлан
        @SerializedName("high_skills")
        val highSkills: String, // Python, Go, PostgreSQL, WebSocket, Docker, REST API, FastAPI, Django, Git, Linux, Nginx, Docker Compose, MongoDB, Redis, RabbitMQ, gRPC, RPC
        @SerializedName("percent_appropriate")
        val percentAppropriate: String, // 90%
        @SerializedName("resume_link")
        val resumeLink: String // /static/resumes/Резюме_Backend_разработчик_Нурлан_Юнусович_Пурханов_от_02_11_2024.pdf
    )
}