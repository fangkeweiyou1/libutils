package com.wushiyi.util

import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by zhangyuncai on 2019/6/27.
 * 收集闪退日志
 */
object CrashHandlerUtil : Thread.UncaughtExceptionHandler {


    //上下文
    private val crashHandlerContext by lazy { UtilInit.utilContext }
    //crash日志的存放位置
    private val PATH by lazy { "${Environment.getExternalStorageDirectory().getPath()}/${AppUtil.getAppName()}Log" }
    //crash日志文件的前缀
    private val FILE_NAME by lazy { AppUtil.getAppName() }
    //crash日志文件的后缀，本质是txt，可以改成任意后缀使用户无法阅读
    private val FILE_NAME_SUFFIX = ".trace"
    //默认的crashHandler，如没有则为null
    private lateinit var mDefaultCrashHandler: Thread.UncaughtExceptionHandler

    init {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    /**
     * 当程序出现未被捕获的异常，系统将会自动调用此方法
     *
     * @param thread
     * @param throwable
     */
    override fun uncaughtException(thread: Thread?, throwable: Throwable) {
        try {
            dumpExceptionToSDCard(throwable)
            uploadExceptionToServer()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        throwable.printStackTrace()
        //如果系统提供了默认的异常处理器，则交给系统去结束程序，否则由自己结束
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(thread, throwable)
        } else {
            val timer = Timer()
            timer.schedule(object : TimerTask() {

                override fun run() {
                    android.os.Process.killProcess(android.os.Process.myPid())
                }
            }, (3 * 1000).toLong())
        }
    }

    /**
     * 将crash日志写入sd卡
     *
     * @param throwable
     */
    private fun dumpExceptionToSDCard(throwable: Throwable) {
        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
            Log.d("tag", "sdcard unmounted")
            return
        }
        val dir = File(PATH)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val current = System.currentTimeMillis()
        val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(Date(current))
        val file = File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX)
        try {
            val printWriter = PrintWriter(BufferedWriter(FileWriter(file)))
            printWriter.println(time)
            dumpPhoneInfo(printWriter)
            printWriter.println()
            throwable.printStackTrace(printWriter)
            printWriter.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 写入手机环境信息
     *
     * @param printWriter
     * @throws PackageManager.NameNotFoundException
     */
    @Throws(PackageManager.NameNotFoundException::class)
    private fun dumpPhoneInfo(printWriter: PrintWriter) {
        val packageManager = crashHandlerContext.getPackageManager()
        val packageInfo =
            packageManager.getPackageInfo(crashHandlerContext.getPackageName(), PackageManager.GET_ACTIVITIES)
        printWriter.print("App Version:")
        printWriter.print(packageInfo.versionName)
        printWriter.print("_")
        printWriter.println(packageInfo.versionCode)

        //android版本号
        printWriter.print("android版本号(Os Version):")
        printWriter.print(Build.VERSION.RELEASE)
        printWriter.print("_")
        printWriter.println(Build.VERSION.SDK_INT)

        //手机制造商
        printWriter.print("手机制造商(Vendor):")
        printWriter.println(Build.MANUFACTURER)

        //手机型号
        printWriter.print("手机型号(Model):")
        printWriter.println(Build.MODEL)

        //cpu架构
        printWriter.print("CPU架构(CPU ABI):")
        printWriter.println(Build.CPU_ABI)
    }

    /**
     * 将crash日志打包上传，此处省略
     */
    private fun uploadExceptionToServer() {
        Log.d("tag", "upload")
    }
}