package com.zhang.jitdemo

import android.app.Application
import com.wushiyi.util.UtilInit

/**
 * Created by zhangyuncai on 2019/6/28.
 */
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        UtilInit.initUtil(this)
    }
}