package com.wushiyi.util.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Created by zhangyuncai on 2019/6/27.
 * 高度==状态栏高度的view
 */
open class StatusHeightView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, getStatusHeight(context))
    }

    private fun getStatusHeight(context: Context): Int {
        var statusBarHeight1 = -1
        //获取status_bar_height资源的ID
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = context.resources.getDimensionPixelSize(resourceId)
        }
        return statusBarHeight1
    }
}