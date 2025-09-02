package com.weather.easyweather.net

open class BaseRepository {

    protected suspend fun <T : Any> safeApiCall(block: suspend () -> ResponseResult<T>): NetResult<T> = try {
        val resp = block()
        NetResult.Success(resp.requireOkOrThrow())
    } catch (t: Throwable) {
        t.toNetResult()
    }

    protected val apiService by lazy {
        RetrofitClient.apiService
    }

}