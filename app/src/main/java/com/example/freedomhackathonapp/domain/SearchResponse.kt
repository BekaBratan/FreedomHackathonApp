package com.example.freedomhackathonapp.domain


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("result")
    val result: List<Result>
) {
    data class Result(
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
                    val text: String // ## Оценка кандидата: Baibolat Bekarys**1. Имя Фамилия:** Baibolat Bekarys**2. Дата рождения и возраст:** 19 февраля 2003, 21 год**3. Страна и город проживания:** Казахстан, Караганда**4. Насколько процентов подходит:** 20%**5. Оценка соответствия:** Не подходит**6. Ключевые навыки и опыт:*** **Специализация:** Программист, разработчик -  указывает на интерес к программированию, но не  специализируется на Python.* **Образование:**  Бакалавр Информационных систем -  основа для будущей работы, но не отражает практических навыков Python. * **Языки:**  Знание английского B2 -  полезно для работы с документацией и коммуникации, но не напрямую связано с Python.**Пояснение:** Резюме Baibolat Bekarys указывает на интерес к программированию, но не содержит информации о навыках работы с Python.  Он позиционирует себя как Android-разработчика, а не Python-программиста.  В резюме отсутствует опыт работы с Python, а образование  хотя и в области информационных систем, не указывает на специализацию в Python-разработке. Таким образом,  Baibolat Bekarys не подходит для вакансии Python-программиста. 
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
            val candidatesTokenCount: Int, // 330
            @SerializedName("promptTokenCount")
            val promptTokenCount: Int, // 483
            @SerializedName("totalTokenCount")
            val totalTokenCount: Int // 813
        )
    }
}