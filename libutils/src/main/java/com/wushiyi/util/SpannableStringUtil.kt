package com.wushiyi.util

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View

/**
 * Created by zhangyuncai on 2019/6/26.
 */
object SpannableStringUtil {
    /**
     * 设置文字中部分颜色及点击效果
     * @return 返回字符串:content,然后textView.text=content;textView.setMovementMethod(LinkMovementMethod.getInstance())
     * @param text 全部字符串
     * @param localText 需要设置点击效果/颜色的字符串
     * @param localColor 点击的文字颜色
     * @param 点击事件
     */
    fun setLocalTextColorOrClick(
        text: String,
        localText: String,
        localColor: Int,
        listener: View.OnClickListener
    ): SpannableString {
        try {
            val spanableInfo = SpannableString(text)
            //获取点击
            val localClickable = LocalClickable(listener, localColor)
            //起始角标
            val start = text.indexOf(localText)
            //终点角标
            val end = start + localText.length
            //设置点击
            spanableInfo.setSpan(localClickable, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            //设置加粗
            spanableInfo.setSpan(StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            return spanableInfo
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return SpannableString(text)
    }

    /**
     * 设置文字中部分颜色
     * @param text 全部字符串
     * @param localText 需要设置颜色的字符串
     * @param localColor 点击的文字颜色
     */
    fun setLocalTextColor(
        text: String,
        localText: String,
        localColor: Int
    ): SpannableString {
        try {
            val spanableInfo = SpannableString(text)
            //起始角标
            val start = text.indexOf(localText)
            //终点角标
            val end = start + localText.length
            //设置颜色
            spanableInfo.setSpan(ForegroundColorSpan(localColor), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            return spanableInfo
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return SpannableString(text)
    }
}