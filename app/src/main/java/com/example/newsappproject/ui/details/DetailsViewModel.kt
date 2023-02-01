package com.example.newsappproject.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappproject.data.db.api.NewsRepository
import com.example.newsappproject.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {

    init{
        getSavedArticle()
    }


    fun getSavedArticle() = viewModelScope.launch(Dispatchers.IO){
        val res = repository.getFavoriteArticles()
        repository.getFavoriteArticles()
    }


    fun saveFavoriteArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        repository.addToFavorite(article)
    }
}