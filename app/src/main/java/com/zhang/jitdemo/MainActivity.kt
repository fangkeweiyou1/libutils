package com.zhang.jitdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wushiyi.util.CacheUtil
import com.wushiyi.util.UtilInit
import com.wushiyi.util.fffBug
import com.wushiyi.util.showToast

class MainActivity : AppCompatActivity() {
    //val b=a?:"true"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        UtilInit.initUtil(applicationContext)
        showToast("设置成功!")

        fffBug(CacheUtil.getCacheSize())
//        windowManager
//        fffBug("width:${windowManager.defaultDisplay.width}")
//        fffBug("height:${windowManager.defaultDisplay.height}")

    }
}
