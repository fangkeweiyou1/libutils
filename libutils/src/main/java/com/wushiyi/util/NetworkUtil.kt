package com.wushiyi.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager
import java.io.IOException
import java.net.HttpURLConnection
import java.net.NetworkInterface
import java.net.SocketException
import java.net.URL

/**
 * Created by zhangyuncai on 2019/6/28.
 */
object NetworkUtil {
    private val networkContext: Context by lazy { UtilInit.utilContext }
    val TIMEOUT = 3000
    /**
     * NetworkAvailable
     */
    var NET_CNNT_BAIDU_OK = 1
    /**
     * No NetworkAvailable
     */
    var NET_CNNT_BAIDU_TIMEOUT = 2
    /**
     * Net No Ready
     */
    var NET_NOT_PREPARE = 3
    /**
     * Net Error
     */
    var NET_ERROR = 4

    /**
     * 网络是否可用
     * @see 需要添加权限 ACCESS_NETWORK_STATE,否则报异常
     */
    @SuppressLint("MissingPermission")
    fun isNetworkAvailable(): Boolean {

        val connectivityManager = networkContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo == null) {
            return false
        }
        return networkInfo.isConnected
    }

    /**
     * 获取本地ip地址
     * 记得加上联网权限
     *
     * @return
     */
    @SuppressLint("MissingPermission")
    fun getLocalIpAddress(): String {
        var localIp = ""
        try {
            //确保联网权限给了 ,确保已经联网了,否则没有IP地址
            if (isNetworkAvailable()) {


                val en = NetworkInterface.getNetworkInterfaces()
                while (en.hasMoreElements()) {
                    val intf = en.nextElement()
                    val enumIpAddr = intf.inetAddresses
                    while (enumIpAddr.hasMoreElements()) {
                        val inetAddress = enumIpAddr.nextElement()
                        if (!inetAddress.isLoopbackAddress) {
                            localIp = inetAddress.hostAddress.toString()
                        }
                    }

                }

            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }

        return localIp
    }

    /**
     * get net state
     *
     * @return
     */
    @SuppressLint("MissingPermission")
    fun getNetState(context: Context): Int {
        try {
            val connectivity = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity != null) {
                val networkinfo = connectivity.activeNetworkInfo
                if (networkinfo != null) {
                    return if (networkinfo.isAvailable && networkinfo.isConnected) {
                        if (!connectionNetwork(context)) {
                            NET_CNNT_BAIDU_TIMEOUT
                        } else {
                            NET_CNNT_BAIDU_OK
                        }
                    } else {
                        NET_NOT_PREPARE
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return NET_ERROR
    }

    /**
     * ping "www.baidu.com"
     *
     * @return
     */
    private fun connectionNetwork(context: Context): Boolean {
        var result = false
        var httpUrl: HttpURLConnection? = null
        try {
            httpUrl = URL("www.baidu.com")
                .openConnection() as HttpURLConnection
            httpUrl.connectTimeout = TIMEOUT
            httpUrl.connect()
            result = true
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            httpUrl?.disconnect()
            httpUrl = null
        }
        return result
    }

    /**
     * check is 2G
     *
     * @return boolean
     */
    @SuppressLint("MissingPermission")
    fun is2G(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return if (activeNetInfo != null && (activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_EDGE
                    || activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_GPRS || activeNetInfo
                .subtype == TelephonyManager.NETWORK_TYPE_CDMA)
        ) {
            true
        } else false
    }

    /**
     * check is3G
     *
     * @return boolean
     */
    @SuppressLint("MissingPermission")
    fun is3G(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return if (activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_MOBILE) {
            true
        } else false
    }

    /**
     * check is Wifi
     *
     * @return boolean
     */
    @SuppressLint("MissingPermission")
    fun isWifi(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return if (activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI) {
            true
        } else false
    }

    /**
     * check is wifi on
     */
    @SuppressLint("MissingPermission")
    fun isWifiEnabled(context: Context): Boolean {
        val mgrConn = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mgrTel = context
            .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return mgrConn.activeNetworkInfo != null && mgrConn
            .activeNetworkInfo.state == NetworkInfo.State.CONNECTED || mgrTel
            .networkType == TelephonyManager.NETWORK_TYPE_UMTS
    }

}