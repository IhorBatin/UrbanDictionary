package com.example.urbandictionarynike.repository.remote

import com.example.urbandictionarynike.BuildConfig
import com.example.urbandictionarynike.repository.CalendarJsonAdapter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private val factory by lazy {
        Moshi.Builder()
            .add(CalendarJsonAdapter)
            .build()
            .let { MoshiConverterFactory.create(it) }
    }

    private val client by lazy {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(interceptor)
        }
        return@lazy clientBuilder.build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com")
            .addConverterFactory(factory)
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val urbanService: UrbanService by lazy { retrofit.create(UrbanService::class.java) }
}