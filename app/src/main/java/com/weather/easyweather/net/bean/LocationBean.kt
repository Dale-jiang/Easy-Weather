package com.weather.easyweather.net.bean

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class NetLocationFirst(
    val ip: String,
    val city: String?,
    val region: String,
    val timezone: String,
    val latitude: String,
    val longitude: String,
    val postal_code: String,
    val country_short: String,
    val country_long: String?,
) : Parcelable

@Keep
@Parcelize
data class NetLocationSecond(
    val ip: String,
    val city: String?,
    val region: String,
    val country: String?,
    val loc: String,
    val org: String,
    val timezone: String,
) : Parcelable