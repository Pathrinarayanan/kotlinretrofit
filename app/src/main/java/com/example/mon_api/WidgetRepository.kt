package com.example.mon_api

class WidgetRepository(private val apiService: ApiService) {

    suspend fun fetchDataFromApi(): ApiResponse {
        return apiService.fetchData()
    }
}
