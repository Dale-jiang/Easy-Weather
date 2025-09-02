package com.weather.easyweather.net.repo

import com.weather.easyweather.net.RetrofitClient
import com.weather.easyweather.net.bean.NetResult
import com.weather.easyweather.net.bean.ResponseResult
import com.weather.easyweather.net.bean.requireOkOrThrow
import com.weather.easyweather.net.bean.toNetResult

open class BaseRepository {

    protected suspend fun <T : Any> safeApiCall(block: suspend () -> ResponseResult<T>): NetResult<T> = try {
        val resp = block()
        NetResult.Success(resp.requireOkOrThrow())
    } catch (t: Throwable) {
        t.toNetResult()
    }

    protected val apiService by lazy { RetrofitClient.apiService }

    protected val locationFirstService by lazy { RetrofitClient.locationFirstService }

    protected val locationSecondService by lazy { RetrofitClient.locationSecondService }
}