package com.example.felipesangiorge.myapplication.services

import android.support.annotation.VisibleForTesting
import com.example.felipesangiorge.myapplication.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    const val SPORTAPP_API_BASE_URL = "http://91333333.ngrok.io"


    private val gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val sportApiRetrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl(SPORTAPP_API_BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    private fun createHttpClient() = OkHttpClient.Builder()

    fun <S> createService(serviceClass: Class<S>): S {
        val httpBuilder = createHttpClient()

        /*httpBuilder.addInterceptor {
            it.proceed(
                it.request().newBuilder().addHeader(
                    "Authorization",
                    "Bearer ${preferenceStorage.meuAPIApiToken}"
                ).build()
            )
        }*/

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            // set your desired log level
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpBuilder.addInterceptor(logging)
        }

        val retrofit = sportApiRetrofitBuilder
            .client(httpBuilder.build())
            .build()

        return retrofit.create(serviceClass)
    }

}