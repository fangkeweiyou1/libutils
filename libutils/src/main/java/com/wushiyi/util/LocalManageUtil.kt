package com.wushiyi.util

import android.os.Build
import android.os.LocaleList
import java.util.*

/**
 * Created by zhangyuncai on 2019/6/27.
 * 语言管理
 */
class LocalManageUtil {
    private val localManageContext by lazy { UtilInit.utilContext }

    /**
     * 获取系统的语言类型
     */
    fun getSysLocale(): Locale {
        val locale: Locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0)
        } else {
            locale = Locale.getDefault()
        }
        return locale
    }
}