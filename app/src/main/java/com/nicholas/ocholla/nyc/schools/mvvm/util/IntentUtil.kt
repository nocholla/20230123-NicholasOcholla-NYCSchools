package com.nicholas.ocholla.nyc.schools.mvvm.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

object IntentUtil {

    private const val TAG = "INTENT_UTIL"

    @JvmStatic
    fun openUrlIntent(context: Context?, url: String?) {
        url?.let { openIntentWithUriAndAction(context, Uri.parse(it), Intent.ACTION_VIEW) }
    }

    @JvmStatic
    fun openIntentWithUriAndAction(context: Context?, uri: Uri?, action: String?) {
        context?.safeStart(Intent(action).apply { data = uri })
    }

    @JvmStatic
    fun Context.safeStart(intent: Intent, onFailure: (() -> Unit)? = null) {
        try {
            startActivity(intent)
        } catch (e : Exception) {
            Log.d(TAG, "Intent with action ${intent.action} could not resolve a target activity")
            onFailure?.invoke()
        }
    }

}