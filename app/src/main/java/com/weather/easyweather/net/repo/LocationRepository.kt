package com.weather.easyweather.net.repo

class LocationRepository : BaseRepository() {

    suspend fun getLocationFirst() = locationFirstService.getLocation()

    suspend fun getLocationSeconde() = locationSecondService.getLocation()

}