package com.laioffer.tinnews.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.laioffer.tinnews.model.NewsResponse
import com.laioffer.tinnews.repository.NewsRepository

class SearchViewModel(private val repository: NewsRepository) : ViewModel() {
    private val searchInput = MutableLiveData<String>()
    fun setSearchInput(query: String) {
        searchInput.value = query
    }

    fun searchNews(): LiveData<NewsResponse?> {
        return Transformations.switchMap(searchInput) { query: String? -> repository.searchNews(query) }
    }

}