package com.wushiyi.util

import java.net.URLDecoder
import java.net.URLEncoder

/**
 * Created by zhangyuncai on 2019/6/28.
 * URLEncoder编码与解码
 */
object URLEncoderUtil {
    /**
     * URLEncoder编码
     */
    @ExperimentalStdlibApi
    fun toURLEncoded(content: String): String {
        val text = String(content.encodeToByteArray())
        return URLEncoder.encode(text, "UTF-8")
    }

    /**
     * URLDecoder解码
     */
    @ExperimentalStdlibApi
    fun toURLDecoder(content: String): String {
        val text = String(content.encodeToByteArray())
        return URLDecoder.decode(text, "UTF-8")
    }
}