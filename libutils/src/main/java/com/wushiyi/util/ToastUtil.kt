package com.wushiyi.util

import android.content.Context
import android.os.Looper
import android.widget.Toast

/**
 * Created by zhangyuncai on 2019/6/26.
 * toast工具类
 */
private lateinit var toastContext: Context

fun setToastContext(context: Context) {
    toastContext = context
}


/**
 * 常用toast
 * @param content,toast内容
 */
fun showToast(content: String) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        Toast.makeText(toastContext, content, Toast.LENGTH_SHORT).show()
    }
}

/**
 * 时间较长的toast
 * @param content,toast内容
 */
fun longToast(content: String) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        Toast.makeText(toastContext, content, Toast.LENGTH_LONG).show()
    }
}