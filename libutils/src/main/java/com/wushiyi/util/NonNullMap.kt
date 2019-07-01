package com.wushiyi.util

import java.util.*

/**
 * Created by zhangyuncai on 2019/6/28.
 * 键值对都不等于NULL的map,方便java类调用
 */
open class NonNullMap : HashMap<String, Any>() {
    override fun put(key: String, value: Any): Any? {
        if (key == null || value == null) {
            return null
        }
        return super.put(key, value)
    }
}