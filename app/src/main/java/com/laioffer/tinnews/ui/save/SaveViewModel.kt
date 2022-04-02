package com.laioffer.tinnews.ui.save

import androidx.lifecycle.ViewModel
import com.laioffer.tinnews.model.Article
import com.laioffer.tinnews.repository.NewsRepository

class SaveViewModel(private val repository: NewsRepository) : ViewModel() {
    val allSavedArticles: LiveData<List<Article?>?>?
        get() = repository.allSavedArticles

    fun deleteSavedArticle(article: Article?) {
        repository.deleteSavedArticle(article)
    }

}