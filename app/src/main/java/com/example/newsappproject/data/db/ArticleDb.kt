package com.example.newsappproject.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class ArticleDb: RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object{
        @Volatile
        private var instance: ArticleDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
            }

        private fun createDatabase(context: Context):ArticleDb {
            return Room.databaseBuilder(
                context.applicationContext,
                ArticleDb::class.java,
                "article_database"
            ).build()


        }
    }

}