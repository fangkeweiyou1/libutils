package com.wushiyi.util

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

/**
 * Created by zhangyuncai on 2019/6/26.
 */
open class LocalClickable(val listener: View.OnClickListener, val localTextColor: Int) : ClickableSpan() {
    override fun onClick(widget: View) {
        listener.onClick(widget)
    }

    override fun updateDrawState(ds: TextPaint) {
        ds.color = localTextColor
        ds.isUnderlineText = false
    }
}