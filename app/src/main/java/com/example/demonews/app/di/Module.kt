package com.example.demonews.app.di

import android.app.Application
import com.example.demonews.model.api.AllApi
import com.example.demonews.model.repository.TopNewsRepository
import com.example.demonews.ui.home.HomeViewModel
import com.example.demonews.utility.BASE_URL
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): AllApi {
        return retrofit.create(AllApi::class.java)
    }
    single { provideUserApi(get()) }
}

val netModule = module {
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okhttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .cache(cache)

        return okhttpClientBuilder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }

    /*
     viewModel { ProfileViewModel(get(), androidContext()) }
     viewModel { DocUploadViewModel() }*/
}

val repositoryModule = module {

    fun provideTopNewsRepository(api: AllApi): TopNewsRepository {
        return TopNewsRepository(api)
    }

    single { provideTopNewsRepository(get()) }

}