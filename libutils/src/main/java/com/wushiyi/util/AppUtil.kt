package com.wushiyi.util

import android.os.Build

/**
 * Created by zhangyuncai on 2019/6/27.
 * 应用图标/名称/包名工具类
 */
object AppUtil {
    private val appContext by lazy { UtilInit.utilContext }

    /**
     * 获取应用名称,如果应用没有设置appname,那么就会闪退
     */
    fun getAppName(): String {
        val packageManager = appContext.getPackageManager()
        val packageInfo = packageManager.getPackageInfo(
            appContext.getPackageName(), 0
        )
        val labelRes = packageInfo.applicationInfo.labelRes
        packageInfo.applicationInfo.labelRes
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
        return packageInfo.versionCode.toLong()
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

    /**
     * 获取包名中最后一个字符串
     */
    fun getPackageNameLastChar(): String {
        val packageManager = appContext.getPackageManager()
        val packageInfo = packageManager.getPackageInfo(
            appContext.getPackageName(), 0
        )
        val charList = packageInfo.packageName.split(".")
        return charList.last()
    }
}