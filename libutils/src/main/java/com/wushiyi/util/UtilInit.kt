package com.wushiyi.util

import android.content.Context

/**
 * Created by zhangyuncai on 2019/6/26.
 * 工具jar赋值context,工具包中所有类的上下文通过此赋值
 */
object UtilInit {
    internal lateinit var utilContext: Context
    fun initUtil(context: Context) {
        utilContext=context
        SharedPreferencesUtils.initSharedPreferences()
    }

    /**
     * 设置数据库名称
     */
    fun setPreferenceFileName(preferenceFileName: String) {
        Preference.preferenceFileName = preferenceFileName
        SharedPreferencesUtils.setPreferenceFileName(preferenceFileName)
    }
}