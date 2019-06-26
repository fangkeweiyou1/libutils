package com.zhang.libutis

import android.content.Context
import android.widget.Toast

/**
 * Created by zhangyuncai on 2019/6/26.
 */
class ToastUtil {
    companion object {
        lateinit var toastContext: Context
        fun toastMsg(msg: String) {
            Toast.makeText(toastContext, msg, Toast.LENGTH_SHORT).show()
        }
    }
}