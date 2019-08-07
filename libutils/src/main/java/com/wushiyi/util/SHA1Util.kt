package com.wushiyi.util

import android.app.Activity
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import java.security.MessageDigest

/**
 * Created by zhangyuncai on 2019/7/2.
 */
object SHA1Util {

    /*
    输入命令行和密码，即可获取SHA1等信息；

命令行：keytool -list -v -keystore c:/Users/Administrator/.android/debug.keystore -alias androiddebugkey （注意目录选择、开发版本、发布版本等问题）

密码：原始密码一般为android，开发者根据实际情况填写。

最好是输入keystore的全路径地址,alias是别名
keystore可以得到创建该文件日期,所有者,发布者等等信息
     */

    /**
     * 获取facebook的密钥散列
     */
    open fun getFacebookSecret(activity: Activity, packageName: String): String {
        var encodeToStringResult = ""
        try {
            val info = activity.getPackageManager().getPackageInfo(
                packageName,
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                encodeToStringResult = Base64.encodeToString(md.digest(), Base64.DEFAULT)
                Log.d("KeyHash:", encodeToStringResult)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return encodeToStringResult

    }
}