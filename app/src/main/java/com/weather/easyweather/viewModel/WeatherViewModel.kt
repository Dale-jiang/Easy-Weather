package com.weather.easyweather.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.easyweather.net.NetResult
import com.weather.easyweather.net.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val repository by lazy {
        WeatherRepository()
    }

    fun test() {
        viewModelScope.launch {
            when (val res = repository.getWeatherData(lat = "30.57", lon = "104.06")) {
                is NetResult.Success -> {
                    // TODO: 渲染 UI
                }

                is NetResult.ApiError -> {
                    // TODO: 显示后端/协议错误
                }

                is NetResult.NetworkError -> {
                    // TODO: 显示网络异常
                }
            }
        }
    }


}