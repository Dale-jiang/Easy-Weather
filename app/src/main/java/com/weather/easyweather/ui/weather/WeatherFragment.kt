package com.weather.easyweather.ui.weather

import android.os.Bundle
import com.weather.easyweather.databinding.FragmentWeatherBinding
import com.weather.easyweather.ui.base.BaseFragment
import com.weather.easyweather.ui.common.MainActivity
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
                val p = (abs(verticalOffset) / total).coerceIn(0f, 1f)

                val threshold = 0.6f

                val headerPhase = (p / threshold).coerceIn(0f, 1f)
                headerContainer.alpha = 1f - headerPhase

                val titlePhase = if (p <= threshold) 0f
                else ((p - threshold) / (1f - threshold)).coerceIn(0f, 1f)

                (requireActivity() as MainActivity).setTitleView(titlePhase)
            }

        }
    }

}