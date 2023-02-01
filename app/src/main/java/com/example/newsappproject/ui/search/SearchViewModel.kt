package com.example.newsappproject.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappproject.data.db.api.NewsRepository
import com.example.newsappproject.models.NewsResponse
import com.example.newsappproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {

    val searchNewsLiveData: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    init {
        getSearchNews("")
    }

    fun getSearchNews(query: String) = viewModelScope.launch {
        searchNewsLiveData.postValue(Resource.Loading())
        val response = repository.getSearchNews(query, searchNewsPage)
        if (response.isSuccessful){
            response.body().let { res ->
                searchNewsLiveData.postValue(Resource.Success(res))

            }
        }else{
            searchNewsLiveData.postValue(Resource.Error(response.message()))
        }
    }
}