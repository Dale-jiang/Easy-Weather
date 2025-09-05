package com.weather.easyweather.ui.dialog

import com.weather.easyweather.R
import com.weather.easyweather.databinding.DialogUnitChangeBinding
import com.weather.easyweather.ui.base.BaseBottomSheetDialogFragment
import com.weather.easyweather.ui.utils.isMetric

class UnitChangeDialog(val callback: () -> Unit) : BaseBottomSheetDialogFragment<DialogUnitChangeBinding>(DialogUnitChangeBinding::inflate) {

    override fun initView() {

        viewBinding?.apply {
            if (isMetric) {
                metric.setBackgroundResource(R.drawable.bg_d2e9ff_12)
                imperial.setBackgroundResource(R.drawable.bg_eef1f4_12)
            } else {
                metric.setBackgroundResource(R.drawable.bg_eef1f4_12)
                imperial.setBackgroundResource(R.drawable.bg_d2e9ff_12)
            }


            metric.setOnClickListener {
                if (isMetric) return@setOnClickListener
                isMetric = true
                callback()
                dismiss()
            }

            imperial.setOnClickListener {
                if (!isMetric) return@setOnClickListener
                isMetric = false
                callback()
                dismiss()
            }

        }

    }

}