package com.weather.easyweather.net.bean

import androidx.annotation.Keep
import retrofit2.HttpException
import java.io.IOException

@Keep
class ApiException(
    val code: Int,
    override val message: String?,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)

/**
 * 顶层响应壳：与彩云返回结构对齐
 * 关键字段：status == "ok" 表示成功；result 为真正业务数据
 */
data class ResponseResult<T>(
    val result: T? = null,
    val timezone: String? = null,
    val status: String? = null
) {
    val success: Boolean get() = status == "ok"
    override fun toString(): String = "ResponseResult(result=$result, status=$status)"
}

sealed class NetResult<out T> {
    data class Success<T>(val data: T) : NetResult<T>()
    data class ApiError(val code: Int, val message: String) : NetResult<Nothing>()
    data class NetworkError(val throwable: Throwable) : NetResult<Nothing>()
}

fun <T : Any> ResponseResult<T>.requireOkOrThrow(): T {
    if (!success) throw ApiException(-1, "status=$status")
    val data = result
    return requireNotNull(data) { "result is null while status=ok" }
}

internal fun Throwable.toNetResult(): NetResult<Nothing> = when (this) {
    is ApiException -> NetResult.ApiError(code, message ?: "ApiException")
    is HttpException -> NetResult.ApiError(code(), message())
    is IOException -> NetResult.NetworkError(this)
    else -> NetResult.NetworkError(this)
}
