package com.example.freedomhackathonapp.presentaion.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freedomhackathonapp.data.ApiService
import com.example.freedomhackathonapp.data.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String> get() = _response

    private val apiService = ServiceBuilder.buildService(ApiService::class.java)

//    fun getResponse() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _response.value = apiService.getProducts().toString()
//        }
//    }
}