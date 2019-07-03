package com.zhang.jitdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wushiyi.util.DisplayUtil
import com.wushiyi.util.LocalManageUtil
import com.wushiyi.util.Preference

class MainActivity : AppCompatActivity() {


    //val b=a?:"true"

    var gender by Preference<Int>("gender", 0)

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocalManageUtil.attachBaseContext(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DisplayUtil.dip2px(300f)
    }

    fun changeLanguage() {
        LocalManageUtil.setConfiguration()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and (Intent.FLAG_ACTIVITY_CLEAR_TASK));
        startActivity(intent)
        finish()
    }
}
