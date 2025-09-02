package com.weather.easyweather.ui.guide

import androidx.activity.addCallback
import com.weather.easyweather.databinding.ActivityOverlayPermissonBinding
import com.weather.easyweather.ui.base.BaseActivity
import com.weather.easyweather.ui.utils.launchActivity

class OverlayPermissionActivity : BaseActivity<ActivityOverlayPermissonBinding>(ActivityOverlayPermissonBinding::inflate) {

    override fun initUI() {
        launchActivity<SplashActivity>(finish = true)
        return
    }

    override fun initListeners() {
        onBackPressedDispatcher.addCallback { }
    }


}