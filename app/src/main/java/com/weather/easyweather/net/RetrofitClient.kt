package com.weather.easyweather.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.weather.easyweather.ui.utils.isDebug
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val CALL_TIMEOUT = 10L
    private const val CONNECT_TIMEOUT = 10L
    private const val IO_TIMEOUT = 10L

    private val gson: Gson by lazy {
        GsonBuilder().serializeNulls().create()
    }

    private val okhttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .callTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC
                }
            )
            .retryOnConnectionFailure(false)
            .build()
    }

    val apiService: Api by lazy {
        Retrofit.Builder()
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Api.BASE_URL)
            .build()
            .create(Api::class.java)
    }

}