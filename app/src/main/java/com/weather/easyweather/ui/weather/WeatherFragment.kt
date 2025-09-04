package com.weather.easyweather.ui.weather

import android.os.Bundle
import com.weather.easyweather.databinding.FragmentWeatherBinding
import com.weather.easyweather.ui.base.BaseFragment
import kotlin.math.abs

class WeatherFragment : BaseFragment<FragmentWeatherBinding>(FragmentWeatherBinding::inflate) {

    companion object {
        private const val ARG_FRAGMENT_TYPE = "arg_fragment_type"

        fun newInstance(type: String): WeatherFragment {
            return WeatherFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_FRAGMENT_TYPE, type)
                }
            }
        }

    }


    override fun initUI() {
        binding.apply {
            appbar.addOnOffsetChangedListener { layout, verticalOffset ->
                val total = layout.totalScrollRange.toFloat().coerceAtLeast(1f)
                val p = (abs(verticalOffset) / total).coerceIn(0f, 1f)  // 0 展开 -> 1 收起
                headerContainer.alpha = 1f - p * 1.2f
                if (headerContainer.alpha < 0f) headerContainer.alpha = 0f
            }

        }
    }

}