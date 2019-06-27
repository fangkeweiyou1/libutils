package com.wushiyi.util

import android.os.Build

/**
 * Created by zhangyuncai on 2019/6/27.
 * 应用图标/名称/包名工具类
 */
object AppUtil {
    private val appContext by lazy { UtilInit.utilContext }

    /**
     * 获取应用名称
     */
    fun getAppName(): String {
        val packageManager = appContext.getPackageManager()
        val packageInfo = packageManager.getPackageInfo(
            appContext.getPackageName(), 0
        )
        val labelRes = packageInfo.applicationInfo.labelRes
        return appContext.getResources().getString(labelRes)
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @return 当前应用的版本名称
     */
    fun getVersionName(): String {
        val packageManager = appContext.getPackageManager()
        val packageInfo = packageManager.getPackageInfo(
            appContext.getPackageName(), 0
        )
        return packageInfo.versionName
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @return 当前应用的版本名称
     */
    fun getVersionCode(): Long {
        val packageManager = appContext.getPackageManager()
        val packageInfo = packageManager.getPackageInfo(
            appContext.getPackageName(), 0
        )
        if (Build.VERSION.SDK_INT >= 28) {
            return packageInfo.longVersionCode
        }
        return packageInfo.versionCode as Long
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    fun getPackageName(): String {
        val packageManager = appContext.getPackageManager()
        val packageInfo = packageManager.getPackageInfo(
            appContext.getPackageName(), 0
        )
        return packageInfo.packageName
    }
}