package com.laioffer.tinnews.network

import android.content.Context
import com.ashokvarma.gander.GanderInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

// HTTP Client
object RetrofitClient {
    // TODO: Assign your API_KEY here
    private const val API_KEY = "104e304654c1461da8b6a6ae943ffe62"
    private const val BASE_URL = "https://newsapi.org/v2/"
    @kotlin.jvm.JvmStatic
    fun newInstance(context: Context?): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HeaderInterceptor())
                .addInterceptor(GanderInterceptor(context!!).showNotification(true))
                .addNetworkInterceptor(StethoInterceptor())
                .build()
        return Retrofit.Builder()
                .baseUrl(BASE_URL) // gson --> java
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    private class HeaderInterceptor : Interceptor {
        @kotlin.jvm.Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val request = original
                    .newBuilder()
                    .header("X-Api-Key", API_KEY)
                    .build()
            return chain.proceed(request)
        }
    }
}