package com.wushiyi.util

import android.content.Context
import android.os.Looper
import android.widget.Toast

/**
 * Created by zhangyuncai on 2019/6/26.
 */
lateinit var toastContext: Context

fun showToast(content: String) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        Toast.makeText(toastContext, content, Toast.LENGTH_SHORT).show()
    }
}

fun longToast(content: String) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        Toast.makeText(toastContext, content, Toast.LENGTH_LONG).show()
    }
}