package com.wushiyi.util

import java.math.BigDecimal

/**
 * Created by zhangyuncai on 2019/6/27.
 * 解决精度问题
 */
object DecimalUtil {

    /**
     * 两个数字相加
     * @param number1 数字1
     * @param number2 数字2
     * @return 返回两个数字之和,异常就返回数字1
     * exp:number1=1.999 number2=2.0000 return 3.9990
     */
    fun add(number1: String, number2: String): String {

        try {
            val bigDecimal_1 = BigDecimal(number1)
            val bigDecimal_2 = BigDecimal(number2)
            val bigDecimal_sum = bigDecimal_1.add(bigDecimal_2)
            return bigDecimal_sum.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 两个数字相减
     * @param number1 数字1
     * @param number2 数字2
     * @return 返回两个数字之差,异常就返回数字1
     */
    fun subtract(number1: String, number2: String): String {
        try {
            val bigDecimal_1 = BigDecimal(number1)
            val bigDecimal_2 = BigDecimal(number2)
            return bigDecimal_1.subtract(bigDecimal_2).toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 两个数字相乘
     * @param number1 数字1
     * @param number2 数字2
     * @return 返回两个数字相乘,异常就返回数字1
     */
    fun multiply(number1: String, number2: String): String {
        try {
            val bigDecimal_1 = BigDecimal(number1)
            val bigDecimal_2 = BigDecimal(number2)
            return bigDecimal_1.multiply(bigDecimal_2).toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    /**
     * 两个数字相乘
     *
     * @param number1    乘数
     * @param number2    被乘数
     * @param scale 小数点保留位数
     */
    fun multiplyWithScale(number1: String, number2: String, scale: Int): String {
        try {
            val bigDecimal_1 = BigDecimal(number1)
            val bigDecimal_2 = BigDecimal(number2)
            var result = bigDecimal_1.multiply(bigDecimal_2)
            result = result.setScale(scale)
            return result.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 两个数字相除
     * @param number1 数字1
     * @param number2 数字2
     * @return 返回两个数字相除,异常就返回数字1
     */
    fun divide(number1: String, number2: String): String {
        try {
            val bigDecimal_1 = BigDecimal(number1)
            val bigDecimal_2 = BigDecimal(number2)
            return bigDecimal_1.divide(bigDecimal_2).toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }


}