package com.example.mon_api

import retrofit2.http.GET

interface ApiService {
    @GET("v3/06780b47-23b7-4218-829b-7e5b7ed30fb3")
    suspend fun fetchData(): ApiResponse
}
