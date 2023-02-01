package com.example.newsappproject.data.db.api

import com.example.newsappproject.data.db.ArticleDao
import com.example.newsappproject.models.Article
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsService,
private val articleDao: ArticleDao) {

    suspend fun getNews(countryCode: String, pageNumber: Int) =
        newsService.getTopHeadlines(countryCode, pageNumber)

    suspend fun getSearchNews(query:String, pageNumber: Int) =
        newsService.getEverything(query = query, page = pageNumber)

    suspend fun getFavoriteArticles() = articleDao.getAllArticles()

    suspend fun addToFavorite(article: Article) = articleDao.insert(article = article)

}