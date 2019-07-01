package com.zhang.jitdemo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wushiyi.util.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    //val b=a?:"true"

    var gender by Preference<Int>("gender", 0)

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocalManageUtil.attachBaseContext(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = bt_main_en.context
        if (context is Activity) {
            fffBug("view上下文是Activity")
        } else {
            fffBug("view上下文不是Activity")
        }



        showToast("设置成功!")

        bt_main_zh.setOnClickListener {
            LocalManageUtil.setAppLanguageSelect(false)
            changeLanguage()
        }
        bt_main_en.setOnClickListener {
            LocalManageUtil.setAppLanguageSelect(true)
            changeLanguage()
        }


//2409:8809:8381:1d86:1172:e96f:eb51:bc49
        fffBug("连接成功:${NetworkUtil.getLocalIpAddress()}")


//        val hashMap = HashMap<String, Any>()
//        hashMap.put("1",null)
    }

    fun changeLanguage() {
        LocalManageUtil.setConfiguration()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and (Intent.FLAG_ACTIVITY_CLEAR_TASK));
        startActivity(intent)
        finish()
    }
}
