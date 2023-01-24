package com.example.newsappproject.data

import com.example.newsappproject.utils.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/v2/everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY,

    )



    suspend fun getTopHeadlines(
        @Query("country") country: String = "ru",
        @Query("page") page: Int=1,
        @Query("apiKey") apiKey: String = API_KEY
    )
}