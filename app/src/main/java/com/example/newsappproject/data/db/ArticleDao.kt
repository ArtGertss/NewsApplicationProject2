package com.example.newsappproject.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.newsappproject.models.Article

@Dao
interface ArticleDao {

    @androidx.room.Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<Article>
//2 функции которые вставляют или удаляют нашу статью

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)


    @Delete()
    suspend fun delete(article: Article)
}