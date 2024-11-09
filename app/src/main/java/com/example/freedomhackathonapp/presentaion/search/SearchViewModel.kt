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
    private val _response = MutableLiveData<SearchResponse>()
    val response: LiveData<SearchResponse> get() = _response


    fun fetch(prompt: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { ServiceBuilder.api.search("$prompt") }.fold(
                onSuccess = {
                    _response.postValue(it)
                },
                onFailure = {
                    it.printStackTrace()
                }
            )
        }
    }
}