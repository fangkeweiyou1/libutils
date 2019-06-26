package com.zhang.jitdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zhang.libutis.ToastUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ToastUtil.toastContext = applicationContext
    }
}
