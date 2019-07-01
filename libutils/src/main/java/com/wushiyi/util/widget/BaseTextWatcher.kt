package com.wushiyi.util.widget

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by zhangyuncai on 2019/6/27.
 * 空实现方便敲代码
 */
open class BaseTextWatcher:TextWatcher {
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}