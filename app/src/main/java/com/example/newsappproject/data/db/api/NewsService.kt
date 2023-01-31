package com.example.newsappproject.data.db.api

import com.example.newsappproject.models.NewsResponse
import com.example.newsappproject.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/v2/everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY,

    ):Response<NewsResponse>


    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "ru",
        @Query("page") page: Int=1,
        @Query("apiKey") apiKey: String = API_KEY
    ):Response<NewsResponse>
}