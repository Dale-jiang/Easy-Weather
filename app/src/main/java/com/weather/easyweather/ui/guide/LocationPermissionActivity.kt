package com.weather.easyweather.ui.guide

import androidx.activity.addCallback
import com.weather.easyweather.databinding.ActivityLocationPermissonBinding
import com.weather.easyweather.ui.base.BaseActivity
import com.weather.easyweather.ui.common.MainActivity
import com.weather.easyweather.ui.utils.hasLocationPermission
import com.weather.easyweather.ui.utils.isGpsEnabled
import com.weather.easyweather.ui.utils.launchActivity

class LocationPermissionActivity : BaseActivity<ActivityLocationPermissonBinding>(ActivityLocationPermissonBinding::inflate) {

    override fun initUI() {

        if (hasLocationPermission() && isGpsEnabled()) {
            launchActivity<MainActivity>(finish = true)
            return
        }

        setView()
    }

    private fun setView() {


    }

    override fun initListeners() {
        onBackPressedDispatcher.addCallback { }
    }


}