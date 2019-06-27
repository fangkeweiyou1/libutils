package com.wushiyi.util

import android.text.TextUtils
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream


/**
 * Created by zhangyuncai on 2018/9/11.
 * 加载图片的路径地址(url或者file)判断
 */
object ImageUtil {


    val HTTP0 = "https:"
    val HTTP = "http://"
    val HTTPS = "https://"
    val FILE = "file://"
    val SD_CARD = "/storage"

    fun addImageDomain(url: String): String {
        if (TextUtils.isEmpty(url)) {
            return ""
        }
        if (url.startsWith(HTTP) || url.startsWith(HTTPS) || url.startsWith(FILE) || url.startsWith(SD_CARD)) {
            return url
        }
        return if (url.startsWith("//")) {
            HTTP0 + url
        } else HTTP + url
    }


    /**
     * 将图片转换成Base64编码的字符串
     */
    fun imageToBase64(path: String): String? {
        if (TextUtils.isEmpty(path)) {
            return null
        }
        var `is`: InputStream? = null
        var data: ByteArray? = null
        var result: String? = null
        try {
            `is` = FileInputStream(path)
            //创建一个字符流大小的数组。
            data = ByteArray(`is`.available())
            //写入数组
            `is`.read(data)
            //用默认的编码格式进行编码
            result = android.util.Base64.encodeToString(data, android.util.Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (null != `is`) {
                try {
                    `is`.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

        }
        return result
    }
}
