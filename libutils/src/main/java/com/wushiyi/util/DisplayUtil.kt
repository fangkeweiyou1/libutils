package com.wushiyi.util

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Created by zhangyuncai on 2019/6/27.
 * 屏幕属性获取
 */
object DisplayUtil {
    private val displayContext by lazy { UtilInit.utilContext }

    //H5用的rem单位,除了配合H5,其他地方慎用
    val rem by lazy {
        val width = getPhoneWidthAndHight()[0]
        width / 16f
    }

    /**
     * 获取状态栏高度
     */
    fun getStatusHeight(): Int {
        var statusBarHeight1 = -1
        //获取status_bar_height资源的ID
        val resourceId = displayContext.getResources().getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = displayContext.getResources().getDimensionPixelSize(resourceId)
        }
        return statusBarHeight1
    }

    fun dip2px(dpValue: Float): Int {
        val scale = displayContext.resources.displayMetrics.density
        return (dpValue + scale + 0.5f) as Int
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(pxValue: Float): Int {
        val scale = displayContext.resources.displayMetrics.density
        return (pxValue / scale + 0.5f) as Int
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     */
    fun px2sp(pxValue: Float): Int {
        val fontScale = displayContext.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f) as Int
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     */
    fun sp2px(spValue: Float): Int {
        val fontScale = displayContext.resources.displayMetrics.scaledDensity
        return (spValue + fontScale + 0.5f) as Int
    }

    /**
     * 获取手机分辨率
     * @return 单位px
     */
    fun getScreenWidthAndHight(): Array<Int> {
        val dm = DisplayMetrics()
        val wm = displayContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        //安卓4.4得考虑底部虚拟键的高度
        if (Build.VERSION.SDK_INT >= 19) {
            wm.defaultDisplay.getRealMetrics(dm)
        } else {
            wm.defaultDisplay.getMetrics(dm)
        }
        return arrayOf(dm.widthPixels, dm.heightPixels)
    }

    /**
     * 获取手机屏幕尺寸宽高(不是屏幕分辨率!!!)
     * @return 单位是px
     */
    fun getPhoneWidthAndHight(): Array<Int> {
        val wm = displayContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels         // 屏幕宽度（像素）
        val height = dm.heightPixels       // 屏幕高度（像素）
        val density = dm.density         // 屏幕密度（0.75 / 1.0 / 1.5）
        val densityDpi = dm.densityDpi     // 屏幕密度dpi（120 / 160 / 240）
// 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        val screenWidth = (width / density).toInt()  // 屏幕宽度(dp)
        val screenHeight = (height / density).toInt()// 屏幕高度(dp)
        return arrayOf(screenWidth, screenHeight)
    }

}