package com.example.freedomhackathonapp.domain


import com.google.gson.annotations.SerializedName

data class VacancyResponse(
    @SerializedName("candidates")
    val candidates: List<Candidate>,
    @SerializedName("modelVersion")
    val modelVersion: String, // gemini-1.5-flash-001
    @SerializedName("usageMetadata")
    val usageMetadata: UsageMetadata
) {
    data class Candidate(
        @SerializedName("content")
        val content: Content,
        @SerializedName("finishReason")
        val finishReason: String, // STOP
        @SerializedName("index")
        val index: Int, // 0
        @SerializedName("safetyRatings")
        val safetyRatings: List<SafetyRating>
    ) {
        data class Content(
            @SerializedName("parts")
            val parts: List<Part>,
            @SerializedName("role")
            val role: String // model
        ) {
            data class Part(
                @SerializedName("text")
                val text: String // ## Оценка резюме:**1. Имя фамилия:  Mamyrov Assanalikhan**2. Дата рождения и возраст: 7 декабря 2003, 20 лет**3. Страна и город проживания: Казахстан, Алматы**4. Насколько процентов подходит: 20%**5. Оценка соответствия: Не подходит **6. Выделенные ключевые навыки и опыт:*** **Опыт разработки: Отсутствует информация о предыдущем опыте работы в разработке, особенно в Python, Go или Docker. * Ключевые навыки: Резюме кандидата демонстрирует навыки Android разработки (Kotlin, Java, SQL, MVVM, REST API, Git, Firebase, Dagger 2), что не соответствует требованиям вакансии.  **Обоснование:Кандидат не обладает необходимым опытом разработки на Python, Go и знаниями Docker.  Он ориентирован на мобильную разработку, что не соответствует требованиям вакансии. Несмотря на знание Java и REST API, которые частично перекликаются с требованиями вакансии,  отсутствует информация о навыках работы с Python и Go. 
            )
        }

        data class SafetyRating(
            @SerializedName("category")
            val category: String, // HARM_CATEGORY_SEXUALLY_EXPLICIT
            @SerializedName("probability")
            val probability: String // NEGLIGIBLE
        )
    }

    data class UsageMetadata(
        @SerializedName("candidatesTokenCount")
        val candidatesTokenCount: Int, // 260
        @SerializedName("promptTokenCount")
        val promptTokenCount: Int, // 393
        @SerializedName("totalTokenCount")
        val totalTokenCount: Int // 653
    )
}