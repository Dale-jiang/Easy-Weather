package com.weather.easyweather.net.api

import com.weather.easyweather.net.bean.ResponseResult
import com.weather.easyweather.net.bean.WeatherInfoBean
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    companion object {
        const val BASE_URL = "https://api.caiyunapp.com/v2.6/yaagR1FZ8GxyZ7X1/"
    }

    @GET("{longitude},{latitude}/weather")
    suspend fun getWeatherData(
        @Path("latitude") latitude: String?,
        @Path("longitude") longitude: String?,
        @Query("lang") lang: String?,
        @Query("dailysteps") dailySteps: Int = 15,
        @Query("hourlysteps") hourlySteps: Int = 72,
        @Query("unit") unit: String = "metric",
    ): ResponseResult<WeatherInfoBean>

}