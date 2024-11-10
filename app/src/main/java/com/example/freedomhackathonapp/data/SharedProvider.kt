package com.example.freedomhackathonapp.data

import android.content.Context
import android.content.SharedPreferences
import com.example.freedomhackathonapp.domain.NewSearchResponse

class SharedProvider(private val context: Context) {

    private val preferences: SharedPreferences
        get() = context.getSharedPreferences("User", Context.MODE_PRIVATE)

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
            preferences.getString("fullName", "no name").toString(),
            preferences.getString("birthDate", "no date").toString(),
            preferences.getString("country", "no country").toString(),
            preferences.getString("education", "no education").toString(),
            preferences.getString("experience", "no experience").toString(),
            preferences.getString("highSkills", "no skills").toString(),
            preferences.getString("conformityAssessment", "no assessment").toString(),
            preferences.getString("percentAppropriate", "no percent").toString(),
            preferences.getString("resumeLink", "no link").toString()
        )
    }
}