package com.wushiyi.util

import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Created by zhangyuncai on 2019/6/26.
 * 常用四舍五入,字符串/数字互转
 */
object StringUtil {

    //RoundingMode.DOWN//不要四舍五入
    private val twolastDF by lazy {
        val decimalFormat = DecimalFormat("#0.00")
        decimalFormat.roundingMode = RoundingMode.HALF_UP//要四舍五入
        decimalFormat
    }
    private val onelastDF = DecimalFormat("#0.0")
    private val IntlastDF = DecimalFormat("#0")

    /**
     * 四舍五入后保留小数点后两位
     * @param input
     * input:1.2345 out:1.23
     * input:1.2 out:1.20
     * input:1.2355 out:1.24
     */
    fun twolastDF(input: Double): String {
        return twolastDF.format(input)
    }
}