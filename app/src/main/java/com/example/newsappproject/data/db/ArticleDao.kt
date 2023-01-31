package com.example.newsappproject.data.db

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.newsappproject.models.Article

interface ArticleDao {

    @androidx.room.Query("SELECT * FROM articles")
    suspend fun getAllArticle(): LiveData<List<Article>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)


    @Delete()
    suspend fun delete(article: Article)
}