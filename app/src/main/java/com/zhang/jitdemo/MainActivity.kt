package com.zhang.jitdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wushiyi.util.fffBug
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fffBug("vp_main:${vp_main==null}")
    }

}
