package com.wushiyi.util

import android.annotation.SuppressLint
import android.util.Log

/**
 * Created by zhangyuncai on 2019/6/26.
 * 常用扩展方法/字段
 */
/**
 * 打印日志
 */
@SuppressLint("LongLogTag")
fun fffBug(content: Any) {
    Log.d("----------->>>>>>>>-----------",content.toString())
}
