package com.wushiyi.util

import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import java.io.File

/**
 * Created by zhangyuncai on 2019/7/3.
 * 适配8.0
 *
 * exp:
 *   <provider
android:name="android.support.v4.content.FileProvider"
android:authorities="${applicationId}.fileProvider"
android:exported="false"
android:grantUriPermissions="true">
<meta-data
android:name="android.support.FILE_PROVIDER_PATHS"
android:resource="@xml/file_paths" />
</provider>
 */
object UriUtil {
    private val uriContext by lazy { UtilInit.utilContext }
    fun getUri(file: File): Uri {
        val uri: Uri
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(uriContext, "${AppUtil.getPackageName()}.fileProvider", file)
        } else {
            uri = Uri.fromFile(file)
        }
        return uri
    }
}