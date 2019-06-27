package com.wushiyi.util

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by zhangyuncai on 2019/6/27.
 * 控制键盘开关的
 */
object InputMethodUtils {

    fun getInputMethodManager(context: Activity): InputMethodManager {
        return context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    }

    /**
     * 打开输入法
     */
    @JvmOverloads
    fun showSoftInput(context: Activity, view: View? = null) {
        val imm = getInputMethodManager(context)
        if (view == null) {
            imm.hideSoftInputFromWindow(context.currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } else {
            view.requestFocus()
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
        }
    }

    fun closeKeyBoard(context: Activity) {
        val view = context.window.peekDecorView()
        if (view != null) {
            val inputmanger = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputmanger.hideSoftInputFromWindow(view.windowToken, 0)
        }
        /*	((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(InfoSrchRsltActivity.this.getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);*/
    }

    fun hideSoftInputFromWindow(context: Activity, view: View) {
        view.clearFocus()
        val imm = getInputMethodManager(context)
        imm.hideSoftInputFromWindow(view.windowToken, 0) //强制隐藏键盘
    }

    //	public void showInputMethod(View view) {
    //		inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    //	}
    //
    //	/**
    //	 * 隐藏输入法
    //	 *
    //	 */
    //	public void dismissInputMethod() {
    //		inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    //	}
    //
    //	public void showAndHideInputMethpd() {
    //		inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    //	}
    //
    //	public boolean isInputOpen() {
    //		return inputMethodManager.isActive();
    //	}
    //


}
/**
 * 打开输入法
 */
