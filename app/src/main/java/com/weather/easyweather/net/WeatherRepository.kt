package com.weather.easyweather.net

class WeatherRepository : BaseRepository() {
    suspend fun getWeatherData(
        lat: String, lon: String, lang: String = "en_US",
        dailySteps: Int = 15, hourlySteps: Int = 72
    ): NetResult<WeatherInfoBean> = safeApiCall {
        apiService.getWeatherData(longitude = lon, latitude = lat, dailySteps = dailySteps, hourlySteps = hourlySteps, lang = lang)
    }
}