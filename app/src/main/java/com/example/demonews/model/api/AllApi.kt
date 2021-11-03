package com.example.demonews.model.api

import android.content.Context
import com.example.demonews.model.entity.*
import com.example.demonews.utility.*
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


interface AllApi {

    companion object {
        fun create(context: Context): AllApi {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val client = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(AllApi::class.java)
        }
    }


    @GET(API_TOP_HEADLINE)
    suspend fun performTopNews(
        @Query("apiKey") apiKey: String
    ): Response<TopHeadlineResponse>

    @GET(API_POPULAR_NEWS)
    suspend fun performPopularNews(
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): Response<TopHeadlineResponse>

}