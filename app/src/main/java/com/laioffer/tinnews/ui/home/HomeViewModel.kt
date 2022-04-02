package com.laioffer.tinnews.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.laioffer.tinnews.model.Article
import com.laioffer.tinnews.repository.NewsRepository

// GOOGLE MVVM provides
class HomeViewModel(private val repository: NewsRepository) : ViewModel() {

    // hold the data
    private val countryInput = MutableLiveData<String>()

    /*
    decouple input and output
     */
    // get input
    fun setCountryInput(country: String) {
        countryInput.value = country
    }// give the input data to getTopHeadlines method
    // repository:: method reference
    // we need to save the state

    // 生产线建好了，只有一条生产线
    // 只需要做一次，新建live data object
    // observe pattern: automatically create an output

    /*
    once countryInput is ready, then trigger getTopHeadlines function to get the output
    repository::getTopHeadlines - method reference (one of the lambda usage)
    ContainingClass::staticMethodName

     */

    // get result
    val topHeadlines: LiveData<NewsResponse?>
        get() =// give the input data to getTopHeadlines method
        // repository:: method reference
        // we need to save the state

        // 生产线建好了，只有一条生产线
        // 只需要做一次，新建live data object
        // observe pattern: automatically create an output

                /*
                once countryInput is ready, then trigger getTopHeadlines function to get the output
                repository::getTopHeadlines - method reference (one of the lambda usage)
                ContainingClass::staticMethodName
        
                 */
            Transformations.switchMap(countryInput) { country: String? -> repository.getTopHeadlines(country) }

    fun setFavoriteArticleInput(article: Article?) {
        repository.favoriteArticle(article)
    }

}