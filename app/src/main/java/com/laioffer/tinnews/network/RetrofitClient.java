package com.laioffer.tinnews.network;

import android.content.Context;

import com.ashokvarma.gander.GanderInterceptor;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// HTTP Client
public class RetrofitClient {

    // TODO: Assign your API_KEY here
    private static final String API_KEY = "104e304654c1461da8b6a6ae943ffe62";
    private static final String BASE_URL = "https://newsapi.org/v2/";

    public static Retrofit newInstance(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new GanderInterceptor(context).showNotification(true))
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                // gson --> java
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    private static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request request = original
                    .newBuilder()
                    .header("X-Api-Key", API_KEY)
                    .build();
            return chain.proceed(request);
        }
    }
}
