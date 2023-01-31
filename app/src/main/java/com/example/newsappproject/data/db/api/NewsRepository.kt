package com.example.newsappproject.data.db.api

import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService) {

    suspend fun getNews(countryCode: String, pageNumber: Int) =
        newsService.getTopHeadlines(countryCode, pageNumber)

}