package com.laioffer.tinnews.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.laioffer.tinnews.TinNewsApplication
import com.laioffer.tinnews.database.TinNewsDatabase
import com.laioffer.tinnews.model.Article
import com.laioffer.tinnews.model.NewsResponse
import com.laioffer.tinnews.network.NewsApi
import com.laioffer.tinnews.network.RetrofitClient.newInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository(context: Context?) {
    private val newsApi: NewsApi?
    private val database: TinNewsDatabase?

    /**
     *
     * @param country - input country
     * @return - return the searching result
     */
    fun getTopHeadlines(country: String?): LiveData<NewsResponse?>? {
        val topHeadlinesLiveData = MutableLiveData<NewsResponse?>()
        newsApi!!.getTopHeadlines(country)
                .enqueue(object : Callback<NewsResponse?> {
                    override fun onResponse(call: Call<NewsResponse?>?, response: Response<NewsResponse?>?) {
                        if (response!!.isSuccessful) {
                            topHeadlinesLiveData.setValue(response.body())
                        } else {
                            topHeadlinesLiveData.setValue(null)
                        }
                    }

                    override fun onFailure(call: Call<NewsResponse?>?, t: Throwable?) {
                        topHeadlinesLiveData.setValue(null)
                    }
                })
        return topHeadlinesLiveData
    }

    fun searchNews(query: String?): LiveData<NewsResponse?>? {
        val everyThingLiveData = MutableLiveData<NewsResponse?>()
        newsApi!!.getEverything(query, 40)
                .enqueue(
                        object : Callback<NewsResponse?> {
                            override fun onResponse(call: Call<NewsResponse?>?, response: Response<NewsResponse?>?) {
                                if (response!!.isSuccessful) {
                                    everyThingLiveData.setValue(response.body())
                                } else {
                                    everyThingLiveData.setValue(null)
                                }
                            }

                            override fun onFailure(call: Call<NewsResponse?>?, t: Throwable?) {
                                everyThingLiveData.setValue(null)
                            }
                        })
        return everyThingLiveData
    }

    // live data is mutable
    fun favoriteArticle(article: Article?): LiveData<Boolean?>? {
        val resultLiveData = MutableLiveData<Boolean?>()
        FavoriteAsyncTask(database, resultLiveData).execute(article)
        return resultLiveData
    }

    // not in the background
    val allSavedArticles: LiveData<MutableList<Article?>?>?
        get() = database!!.articleDao()!!.allArticles

    // delete happens at the background
    fun deleteSavedArticle(article: Article?) {
        AsyncTask.execute { database!!.articleDao()!!.deleteArticle(article) }
    }

    private class FavoriteAsyncTask(private val database: TinNewsDatabase?, private val liveData: MutableLiveData<Boolean?>?) : AsyncTask<Article?, Void?, Boolean?>() {

        // do it in another thread
        // return value: true: successfully saved; false: did not save
        override fun doInBackground(vararg articles: Article?): Boolean? {
            val article = articles[0]
            try {
                database!!.articleDao()!!.saveArticle(article)
            } catch (e: Exception) {
                return false
            }
            return true
        }

        // go to the main thread, write into the data
        override fun onPostExecute(success: Boolean?) {
            liveData!!.value = success
        }

    }

    init {
        newsApi = newInstance(context).create(NewsApi::class.java)
        // access the database by context(source provider)
        database = (context!!.applicationContext as TinNewsApplication).database
    }
}