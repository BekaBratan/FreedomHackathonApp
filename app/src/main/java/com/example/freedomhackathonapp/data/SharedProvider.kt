package com.example.freedomhackathonapp.data

import android.content.Context
import android.content.SharedPreferences
import com.example.freedomhackathonapp.domain.NewSearchResponse

class SharedProvider(private val context: Context) {

    private val preferences: SharedPreferences
        get() = context.getSharedPreferences("User", Context.MODE_PRIVATE)

    private val search: SharedPreferences
        get() = context.getSharedPreferences("Search", Context.MODE_PRIVATE)

    fun saveSearch(search: String, specialization: String, from: String) {
        val sharedPref = context.getSharedPreferences("Search", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("search", search)
        editor.putString("specialization", specialization)
        editor.putString("from", from)
        editor.apply()
    }

    fun setFrom(from: String) {
        val sharedPref = context.getSharedPreferences("Search", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("from", from)
        editor.apply()
    }

    fun getFrom(): String {
        return search.getString("from", "no from").toString()
    }

    fun getSearch(): String {
        return search.getString("search", "no search").toString()
    }

    fun getSpecialization(): String {
        return search.getString("specialization", "no specialization").toString()
    }

    fun clearShared() {
        preferences.edit().clear().apply()
    }

    fun saveUser(user: NewSearchResponse.Data) {
        val sharedPref = context.getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear()
        editor.putString("fullName", user.fullName)
        editor.putString("birthDate", user.birthDate)
        editor.putString("country", user.country)
        editor.putString("education", user.education)
        editor.putString("experience", user.experience)
        editor.putString("highSkills", user.highSkills)
        editor.putString("conformityAssessment", user.conformityAssessment)
        editor.putString("percentAppropriate", user.percentAppropriate)
        editor.putString("resumeLink", user.resumeLink)
        editor.apply()
    }

    fun getUser(): NewSearchResponse.Data {
        return NewSearchResponse.Data(
            fullName = preferences.getString("fullName", "no name").toString(),
            birthDate = preferences.getString("birthDate", "no date").toString(),
            country = preferences.getString("country", "no country").toString(),
            education = preferences.getString("education", "no education").toString(),
            experience = preferences.getString("experience", "no experience").toString(),
            highSkills = preferences.getString("highSkills", "no skills").toString(),
            conformityAssessment = preferences.getString("conformityAssessment", "no assessment").toString(),
            percentAppropriate = preferences.getString("percentAppropriate", "no percent").toString(),
            resumeLink = preferences.getString("resumeLink", "no link").toString()
        )
    }
}