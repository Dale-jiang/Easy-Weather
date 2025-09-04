package com.weather.easyweather.ui.common

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.weather.easyweather.databinding.ActivityMainBinding
import com.weather.easyweather.ui.base.BaseActivity
import com.weather.easyweather.ui.utils.edgeToEdge
import com.weather.easyweather.ui.weather.WeatherFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onAttachedToWindow() {
        edgeToEdge(parentView = binding.root)
    }

    private val mFragments by lazy {
        listOf(WeatherFragment.newInstance(""))
    }

    override fun initUI() {
        bindViewPager(mFragments)
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


}