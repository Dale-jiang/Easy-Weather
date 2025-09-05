package com.weather.easyweather.ui.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ScreenUtils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.weather.easyweather.R
import kotlin.math.roundToInt

inline fun <reified T : Activity> Activity.launchActivity(
    options: Bundle? = null,
    finish: Boolean = false,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = Intent(this, T::class.java).apply(init)
    if (options != null) {
        startActivity(intent, options)
    } else {
        startActivity(intent)
    }
    if (finish) this.finish()
}

fun AppCompatActivity.edgeToEdge(parentView: ViewGroup? = null, topPadding: Boolean = true, bottomPadding: Boolean = true, dark: Boolean = false) {
    runCatching {
        enableEdgeToEdge(statusBarStyle = if (dark) SystemBarStyle.dark(Color.TRANSPARENT) else SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT))
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { _, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            if (parentView != null) {
                parentView.setPadding(0, if (topPadding) systemBarsInsets.top else 0, 0, if (bottomPadding) systemBarsInsets.bottom else 0)
            } else {
                this@edgeToEdge.window.decorView.setPadding(0, if (topPadding) systemBarsInsets.top else 0, 0, if (bottomPadding) systemBarsInsets.bottom else 0)
            }
            insets
        }
    }.onFailure { throwable ->
        throwable.printStackTrace()
    }
}

fun Context.hasOverlayPermissionGranted(): Boolean {
    return Settings.canDrawOverlays(this)
}

private fun Context.isGranted(p: String) =
    ContextCompat.checkSelfPermission(this, p) == PackageManager.PERMISSION_GRANTED

fun Context.hasLocationPermission(): Boolean {
    return isGranted(Manifest.permission.ACCESS_COARSE_LOCATION) || isGranted(Manifest.permission.ACCESS_FINE_LOCATION)
}

fun Context.isGpsEnabled(): Boolean {
    val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val hasGps = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)
    if (!hasGps) return false
    return try {
        lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
    } catch (_: Exception) {
        false
    }
}


private val Context.density get() = resources.displayMetrics.density

fun Context.showLoadingDialog(
    message: CharSequence? = null,
    cancelable: Boolean = false
): AlertDialog {
    val padding = if (message.isNullOrBlank()) (50 * density).roundToInt() else (16 * density).roundToInt()
    val size = (32 * density).roundToInt()

    val progress = CircularProgressIndicator(this).apply {
        isIndeterminate = true
        layoutParams = LinearLayout.LayoutParams(size, size).apply {
            gravity = Gravity.CENTER_HORIZONTAL
        }
    }

    val content = LinearLayout(this).apply {
        background = ContextCompat.getDrawable(context, R.drawable.bg_shape_white_10)
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        setPadding(padding, padding, padding, padding)
        addView(progress)

        if (!message.isNullOrBlank()) {
            val tv = TextView(this@showLoadingDialog).apply {
                setTextColor(ContextCompat.getColor(context, R.color.c_main))
                text = message
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { topMargin = (12 * resources.displayMetrics.density).roundToInt() }
                gravity = Gravity.CENTER
            }
            addView(tv)
        }
    }

    return MaterialAlertDialogBuilder(this)
        .setCancelable(cancelable)
        .setView(content)
        .create().apply {
            window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            setOnShowListener {
                window?.setLayout(
                    ScreenUtils.getScreenWidth() - ConvertUtils.dp2px(80f),
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
            }
            show()
        }
}


