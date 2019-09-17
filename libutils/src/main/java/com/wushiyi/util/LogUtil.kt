package com.wushiyi.util

import android.annotation.SuppressLint
import android.util.Log

/**
 * Created by zhangyuncai on 2019/6/28.
 */
object LogUtil {
    /**
     * 默认开启Log
     */
    var debug = true

    private val TAG = "-->>-->>--"
    private val LEVEL_I = "i"
    private val LEVEL_D = "d"
    private val LEVEL_W = "w"
    private val LEVEL_V = "v"
    private val LEVEL_E = "e"

    @SuppressLint("LongLogTag")
    fun d(content: Any) {
        if (debug) {
            Log.w(TAG, content.toString())
        }
    }

    @SuppressLint("LongLogTag")
    fun e(content: Any) {
        if (debug) {
            Log.e(TAG, content.toString())
        }
    }
}