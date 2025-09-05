package com.weather.easyweather.ui.settings

import androidx.activity.addCallback
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import com.weather.easyweather.APP
import com.weather.easyweather.databinding.ActivitySettingsBinding
import com.weather.easyweather.ui.base.BaseActivity

class SettingsActivity : BaseActivity<ActivitySettingsBinding>(ActivitySettingsBinding::inflate) {

    override fun onResume() {
        super.onResume()
        APP.lifecycleManager.setNavigatingToSettings(false)
    }

    override fun initUI() {

        onBackPressedDispatcher.addCallback {
            finish()
        }
        binding.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnPolicy.setOnClickListener {
            APP.lifecycleManager.setNavigatingToSettings(true)
            runCatching { CustomTabsIntent.Builder().build().launchUrl(this@SettingsActivity, "about:blank".toUri()) }
        }

        binding.btnUnit.setOnClickListener {
            // TODO:  
        }

    }

}

