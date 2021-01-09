package com.laioffer.tinnews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.repository.NewsRepository;

import model.Article;
import model.NewsResponse;

// GOOGLE MVVM provides
public class HomeViewModel extends ViewModel {

    private final NewsRepository repository;
    // hold the data
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();

    public HomeViewModel(NewsRepository newsRepository) {
        this.repository = newsRepository;
    }

    public void setCountryInput(String country) {
        countryInput.setValue(country);
    }

    public LiveData<NewsResponse> getTopHeadlines() {
        // give the input data to getTopHeadlines method
        // repository:: method reference
        // we need to save the state

        // 生产线建好了，只有一条生产线
        // 只需要做一次，新建live data object
        // observe pattern: automatically create an output
        return Transformations.switchMap(countryInput, repository::getTopHeadlines);
    }

    public void setFavoriteArticleInput(Article article) {
        repository.favoriteArticle(article);
    }
}

