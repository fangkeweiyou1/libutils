package com.zhang.jitdemo

import android.app.Application
import com.wushiyi.util.UtilInit

/**
 * Created by zhangyuncai on 2019/6/28.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        UtilInit.initUtil(this)

        val animal = Animal()
        try {

            val clazz = Animal::class.java
            val method = clazz.getDeclaredMethod("getDog")
            method.isAccessible = true
            val dog = method.invoke(animal) as Dog
            println("dog:${dog.name}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}