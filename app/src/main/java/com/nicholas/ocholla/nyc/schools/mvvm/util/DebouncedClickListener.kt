package com.nicholas.ocholla.nyc.schools.mvvm.util

import android.os.SystemClock
import android.view.View
import java.util.WeakHashMap

private const val minimumInterval = 1000L

abstract class DebouncedClickListener : View.OnClickListener {
    abstract fun onClicked(v: View?)

    private val lastClickMap: MutableMap<View, Long> = WeakHashMap()

    override fun onClick(clickedView: View) {
        val previousClickTimestamp = lastClickMap[clickedView]
        val currentTimestamp = SystemClock.uptimeMillis()

        lastClickMap[clickedView] = currentTimestamp
        if (previousClickTimestamp == null || currentTimestamp - previousClickTimestamp.toLong() > minimumInterval) {
            onClicked(clickedView)
        }
    }
}