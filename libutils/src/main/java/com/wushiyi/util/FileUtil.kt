package com.wushiyi.util

import android.os.Environment
import java.io.File

/**
 * Created by zhangyuncai on 2019/6/27.
 * 查看文件是否存在不需要权限,但是读取,写入必须要权限
 * 需要初始化
 */
object FileUtil {
    //上下文
    private val fileContext by lazy { UtilInit.utilContext }
    //APP根目录
    //默认文件名称为应用名称
    internal var fileDirName: String = ""
        get() {
            if (field.isNullOrEmpty()) {
                field = AppUtil.getPackageNameLastChar()
            }
            return field
        }

    /**
     * 创建图片文件路径
     */
    fun createImagePath(imageName: String): String {
        return "${getFileDir("image")}/$imageName.png"
    }

    /**
     *创建文件夹(创建文件之前必须先创建文件夹)
     * @param 需要创建的文件夹名称
     * @return 储存卡/app根目录/文件夹名称
     */
    fun getFileDir(folder: String): String {
        //得到储存卡根路径
        val dir = getDir()
        //拼接路径
        val path = "$dir/$fileDirName/$folder"
        //得到文件对象
        val file = File(path)
        //如果文件夹不存在,创建文件夹
        if (!file.exists()) {
            file.mkdirs()
        }
        return path
    }

    /**
     * 获取外部储存根路径,固定路径为 0/
     * @return 小米手机返回路径是 0/
     */
    fun getDir(): String {
        val state = Environment.getExternalStorageState()
        return if (Environment.MEDIA_MOUNTED == state) {
            Environment.getExternalStorageDirectory().absolutePath
        } else {
            fileContext.getFilesDir().getAbsolutePath()
        }
    }
}