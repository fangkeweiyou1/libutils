package com.wushiyi.util

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * Created by zhangyuncai on 2019/6/27.
 * 管理Activity工具类(安全退出APP机制)
 */
object AppManager {
    val activityStack = Stack<Activity>()
    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * 获取当前Activity(堆栈中最后一个压入的)
     */
    fun currentActivity(): Activity {
        return activityStack.lastElement()
    }

    /**
     * 结束当前Activity(堆栈中最后一个压入的)
     */
    fun finishCurrentActivity() {
        finishActivity(activityStack.lastElement())
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity) {
        activity.finish()
        activityStack.remove(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(clazz: Class<Activity>) {
        for (activity in activityStack) {
            if (activity::class.java == clazz) {
                AppManager.finishActivity(activity)
                break
            }
        }
    }

    /**
     *关闭所有界面
     * @param excludeClazz 给定的activity排除掉
     */
    fun finishAllActivity(excludeClazz: Class<Activity>?) {
        val iterator = activityStack.iterator()
        while (iterator.hasNext()) {
            val activity = iterator.next()
            if (excludeClazz != null && activity::class.java == excludeClazz) {
                continue
            }
            finishActivity(activity)
        }
    }

    /**
     * 退出应用程序
     */
    fun AppExit() {
        try {


            var activityManager: ActivityManager? = null
            var packageName: String? = ""
            if (activityStack.size > 0) {
                val activity = activityStack[0]
                packageName = activity.packageName
                activityManager = activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            }
            activityManager?.run {
                this.restartPackage(packageName!!)
                System.exit(0)
                android.os.Process.killProcess(android.os.Process.myPid())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 移除activity
     */
    fun deleteActviity(activity: Activity) {
        activityStack.remove(activity)
    }
}