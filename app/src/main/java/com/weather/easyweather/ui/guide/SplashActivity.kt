package com.weather.easyweather.ui.guide

import android.annotation.SuppressLint
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import com.weather.easyweather.databinding.ActivitySplashBinding
import com.weather.easyweather.ui.base.BaseActivity
import com.weather.easyweather.ui.common.MainActivity
import com.weather.easyweather.ui.utils.edgeToEdge
import com.weather.easyweather.ui.utils.launchActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    override fun onAttachedToWindow() {
        edgeToEdge(topPadding = false, bottomPadding = true)
    }

    override fun initUI() {
        loading()
    }

    private fun loading() {
        lifecycleScope.launch {
            val startTime = System.currentTimeMillis()
            val adReady = awaitAdsReady(timeout = 15_000L, pollInterval = 200L)
            enforceMinimumDisplayTime(startTime, minTime = 3_000L)
            handlePostLoad(adReady)
        }
    }


    private suspend fun awaitAdsReady(timeout: Long, pollInterval: Long): Boolean {
        return withTimeoutOrNull(timeout) {
//            while (!checkAds()) {
//                delay(pollInterval)
//            }
            true
        } ?: false
    }

    private suspend fun enforceMinimumDisplayTime(startTime: Long, minTime: Long) {
        val elapsed = System.currentTimeMillis() - startTime
        if (elapsed < minTime) {
            delay(minTime - elapsed)
        }
    }


    private fun handlePostLoad(adReady: Boolean) {
        navigateNext()
    }


    override fun initData() {
        onBackPressedDispatcher.addCallback { }
    }


    private fun navigateNext() {
        launchActivity<MainActivity>(finish = true)
    }

}

