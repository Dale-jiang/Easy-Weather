package com.weather.easyweather.net.api

import com.weather.easyweather.net.bean.NetLocationFirst
import com.weather.easyweather.net.bean.NetLocationSecond
import retrofit2.http.GET
import retrofit2.http.Headers

interface LocationApiFirst {

    companion object {
        const val BASE_URL = "https://api.infoip.io/"
    }

    @Headers("Referer: no-referrer", "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/139.0.0.0 Safari/537.36")
    @GET("/")
    suspend fun getLocation(): NetLocationFirst?

}


interface LocationApiSecond {

    companion object {
        const val BASE_URL = "https://ipinfo.io/"
    }

    @Headers("Referer: no-referrer", "User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/139.0.0.0 Safari/537.36")
    @GET("json/")
    suspend fun getLocation(): NetLocationSecond?

}