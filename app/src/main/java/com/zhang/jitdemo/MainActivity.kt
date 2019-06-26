package com.zhang.jitdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wushiyi.util.showToast
import com.wushiyi.util.toastContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        ToastUtil.toastContext = applicationContext
        toastContext=applicationContext
//        showToast()
    }
}
