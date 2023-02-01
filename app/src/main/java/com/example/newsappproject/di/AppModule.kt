package com.example.newsappproject.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.newsappproject.data.db.ArticleDao
import com.example.newsappproject.data.db.ArticleDb
import com.example.newsappproject.data.db.api.NewsService
import com.example.newsappproject.models.Article
import com.example.newsappproject.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun baseUrl() = BASE_URL


    //HttpLoggingInterceptor используется для вывода информации запросов на сервер в логи
    @Provides
    fun logging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okhttpClient() = OkHttpClient.Builder()
        .addInterceptor(logging())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): NewsService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient())
        .build()
        .create(NewsService::class.java)


    @Provides
    @Singleton
    fun provideArticleDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ArticleDb::class.java, "article_database").build()

    @Provides
    fun provideArticleDao(appDatabase: ArticleDb): ArticleDao{
        return appDatabase.getArticleDao()
    }
}