package com.wushiyi.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by zhangyuncai on 2019/6/27.
 * 时间格式化工具类
 */
object TimeUtil {
    val format_dd_MM_yyyy = SimpleDateFormat("dd-MM-yyyy")
    val text_dd_MM_yyyy = "dd-MM-yyyy"

    /**
     *所有解析时间格式异常全丢在这里
     * @param inputDate 时间格式的字符串 例如:2017年04月14日
     * @param format  yyyy年MM月dd日
     * @return Date exp:inputDate="2017年04月14日",format:yyyy年MM月dd日
     */
    fun parseDate(inputDate: String, format: SimpleDateFormat): Date {
        try {
            return format.parse(inputDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val c = Calendar.getInstance()
        return c.time
    }
}