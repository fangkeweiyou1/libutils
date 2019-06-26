package com.wushiyi.util

import android.content.Context

/**
 * Created by zhangyuncai on 2019/6/26.
 * 工具jar赋值context
 */
object UtilInit {
    fun initUtil(context: Context) {
        setToastContext(context)
        Preference.initPreference(context)
    }
}