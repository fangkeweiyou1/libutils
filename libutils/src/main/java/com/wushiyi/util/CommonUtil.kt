package com.wushiyi.util

import android.annotation.SuppressLint

/**
 * Created by zhangyuncai on 2019/6/26.
 * 常用扩展方法/字段
 */
/**
 * 打印日志,方便敲代码
 */
@SuppressLint("LongLogTag")
fun fffBug(content: Any) {
    LogUtil.d(content)
}

fun eeeBug(content: Any) {
    println("-->>-->>--:${content.toString()}")
}
