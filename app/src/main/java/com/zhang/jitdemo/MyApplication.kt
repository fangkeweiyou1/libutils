package com.zhang.jitdemo

import android.app.Application
import com.wushiyi.util.UtilInit
import com.wushiyi.util.ValidatorUtil
import com.wushiyi.util.eeeBug

/**
 * Created by zhangyuncai on 2019/6/28.
 */
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        UtilInit.initUtil(this)

        val mobile="16620046114"
        eeeBug("是否是手机号:${ValidatorUtil.isMobile(mobile)}")
    }
}