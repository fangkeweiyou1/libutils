package com.wushiyi.util.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by zhangyuncai on 2019/6/27.
 * 宽高相等的ImageView
 */
open class SqureImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}