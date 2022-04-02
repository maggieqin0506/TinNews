package com.laioffer.tinnews.network

import com.laioffer.tinnews.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    fun getTopHeadlines(@Query("country") country: String?): Call<NewsResponse?>?

    @GET("everything")
    fun getEverything(
            @Query("q") query: String?, @Query("pageSize") pageSize: Int): Call<NewsResponse?>?
}