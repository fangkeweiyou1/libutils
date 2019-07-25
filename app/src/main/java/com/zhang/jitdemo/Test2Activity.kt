package com.zhang.jitdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wushiyi.util.Preference
import com.wushiyi.util.SharedPreferencesUtils
import com.wushiyi.util.eeeBug

/**
 * Created by zhangyuncai on 2019/7/20.
 */
class Test2Activity :AppCompatActivity(){
    var name by Preference("name","你好")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)

        eeeBug("1name:$name")
        name="阿玛尼"
        eeeBug("2name:$name")
        SharedPreferencesUtils.saveString("name","阿里巴巴")
        eeeBug("3name:$name")
        name="谷歌"
        val string = SharedPreferencesUtils.loadString("name")
        eeeBug("string:$string")
        val jj="(1[0-9][0-9]|15[0-9]|18[0-9])\\d{8}"

    }
}