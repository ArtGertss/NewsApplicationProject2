package com.example.newsappproject.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsappproject.models.Article


@Database(entities = [Article::class], version = 1, exportSchema = true)
abstract class ArticleDb: RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao


}