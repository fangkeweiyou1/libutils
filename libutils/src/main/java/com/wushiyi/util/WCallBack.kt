package com.wushiyi.util

/**
 * Created by zhangyuncai on 2019/6/27.
 * 本应用专用回调,方便敲代码
 */
interface WCallBack<T> {
    fun callback(t: T)
}