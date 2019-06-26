package com.zhang.jitdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wushiyi.util.UtilInit
import com.wushiyi.util.showToast

class MainActivity : AppCompatActivity() {
    //val b=a?:"true"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        UtilInit.initUtil(applicationContext)
        showToast("设置成功!")
//        toastContext = applicationContext
//        val twolastDF = StringUtil.twolastDF(1.2355)
//        fffBug(twolastDF)
////        twolastDF()
//        Preference.initPreference(applicationContext,"111")

    }
}
