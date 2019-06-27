package com.wushiyi.util

import android.os.Environment
import java.io.File

/**
 * Created by zhangyuncai on 2019/6/27.
 * 文件缓存计算工具
 */
object CacheUtil {
    private val cacheContext by lazy { UtilInit.utilContext }

    fun getCacheSize(): String {
        // 计算缓存大小
        var fileSize: Long = 0
        var cacheSize = "0KB"
        //exp:filesDir:/data/user/0/com.zhang.jitdemo/files
        val filesDir = cacheContext.filesDir
        //exp:cacheDir:/data/user/0/com.zhang.jitdemo/files
        val cacheDir = cacheContext.cacheDir
        //exp:ExternalCacheDir:/storage/emulated/0/Android/data/com.zhang.jitdemo/cache
        val ExternalCacheDir = cacheContext.externalCacheDir

        fileSize += getDirSize(filesDir)
        fileSize += getDirSize(cacheDir)
        fileSize += getDirSize(ExternalCacheDir)
        if (fileSize > 0) {
            cacheSize = formatFileSize(fileSize)
        }
        return cacheSize
    }

    fun cleanApplicationCache() {
        cleanInternalCache()
        cleanExternalCache()
        cleanFiles()
    }

    /**
     * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
     */
    fun cleanInternalCache() {
        deleteFilesByDirectory(cacheContext.cacheDir)
    }

    /**
     * 清除/data/data/com.xxx.xxx/files下的内容
     */
    fun cleanFiles() {
        deleteFilesByDirectory(cacheContext.filesDir)
    }

    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     */
    fun cleanExternalCache() {
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            deleteFilesByDirectory(cacheContext.externalCacheDir)
        }
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     */
    private fun deleteFilesByDirectory(directory: File?) {
        if (directory != null && directory.exists() && directory.isDirectory) {
            for (item in directory.listFiles()!!) {
                if (item.isDirectory) {
                    deleteFilesByDirectory(item)
                }
                item.delete()
            }
            directory.delete()
        }
    }

    /**
     * 获取目录文件大小
     */
    fun getDirSize(dir: File?): Long {
        if (dir == null) {
            return 0
        }
        if (!dir.isDirectory) {
            return 0
        }
        var dirSize: Long = 0
        val files = dir.listFiles()
        for (file in files!!) {
            if (file.isFile) {
                dirSize += file.length()
            } else if (file.isDirectory) {
                dirSize += file.length()
                dirSize += getDirSize(file) // 递归调用继续统计
            }
        }
        return dirSize
    }

    /**
     * 转换文件大小
     *
     * @return B/KB/MB/GB
     */
    fun formatFileSize(fileS: Long): String {
        val df = java.text.DecimalFormat("#.00")
        val fileSizeString: String
        if (fileS < 1024) {
            fileSizeString = df.format(fileS.toDouble()) + "B"
        } else if (fileS < 1048576) {
            fileSizeString = df.format(fileS.toDouble() / 1024) + "KB"
        } else if (fileS < 1073741824) {
            fileSizeString = df.format(fileS.toDouble() / 1048576) + "MB"
        } else {
            fileSizeString = df.format(fileS.toDouble() / 1073741824) + "G"
        }
        return fileSizeString
    }
}