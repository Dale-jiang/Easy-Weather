package com.weather.easyweather.ui.common

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.blankj.utilcode.util.ToastUtils
import com.weather.easyweather.databinding.ActivityMainBinding
import com.weather.easyweather.ui.base.BaseActivity
import com.weather.easyweather.ui.settings.SettingsActivity
import com.weather.easyweather.ui.utils.edgeToEdge
import com.weather.easyweather.ui.weather.WeatherFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onAttachedToWindow() {
        edgeToEdge(parentView = binding.root)
    }

    private val settingsLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            ToastUtils.showLong("单位变换")
        }
    }

    private val mFragments by lazy {
        listOf(WeatherFragment.newInstance(""))
    }

    override fun initUI() {
        bindViewPager(mFragments)
    }

    override fun initListeners() {
        binding.btnMore.setOnClickListener {
            settingsLauncher.launch(Intent(this@MainActivity, SettingsActivity::class.java))
        }
    }

    private fun bindViewPager(fragments: List<Fragment>) {
        binding.viewPager.apply {
            offscreenPageLimit = fragments.size
            adapter = object : FragmentStateAdapter(this@MainActivity) {
                override fun getItemCount() = fragments.size
                override fun createFragment(position: Int) = fragments[position]
            }
        }

    }


    fun setTitleView(alpha: Float) {
        binding.tvTitle.alpha = alpha
        if (alpha > 1) binding.tvTitle.alpha = 1f
    }

}