package com.example.freedomhackathonapp.presentaion.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freedomhackathonapp.data.ApiService
import com.example.freedomhackathonapp.data.ServiceBuilder
import com.example.freedomhackathonapp.domain.SearchResponse
import com.example.freedomhackathonapp.domain.VacancyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    private val _response = MutableLiveData<List<SearchResponse>>()
    val response: LiveData<List<SearchResponse>> get() = _response

    private val apiService = ServiceBuilder.buildService(ApiService::class.java)

    fun fetch(prompt: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _response.value = apiService.search("$prompt")
        }
    }
}