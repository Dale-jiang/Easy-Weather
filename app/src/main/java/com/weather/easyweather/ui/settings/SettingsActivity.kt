package com.weather.easyweather.ui.settings

import androidx.activity.addCallback
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import com.weather.easyweather.APP
import com.weather.easyweather.R
import com.weather.easyweather.databinding.ActivitySettingsBinding
import com.weather.easyweather.ui.base.BaseActivity
import com.weather.easyweather.ui.dialog.UnitChangeDialog
import com.weather.easyweather.ui.utils.isMetric

class SettingsActivity : BaseActivity<ActivitySettingsBinding>(ActivitySettingsBinding::inflate) {

    override fun onResume() {
        super.onResume()
        APP.lifecycleManager.setNavigatingToSettings(false)
    }

    private var tag = false

    override fun initUI() {

        binding.tvUnit.text = getString(if (isMetric) R.string.metric else R.string.imperial)

        onBackPressedDispatcher.addCallback {
            if (tag) setResult(RESULT_OK)
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
            UnitChangeDialog {
                tag = true
                binding.tvUnit.text = getString(if (isMetric) R.string.metric else R.string.imperial)
            }.show(supportFragmentManager, "UnitChangeDialog")
        }

    }

}

