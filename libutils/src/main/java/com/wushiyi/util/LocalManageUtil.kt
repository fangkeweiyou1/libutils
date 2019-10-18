package com.wushiyi.util

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.LocaleList
import java.util.*

/**
 * Created by zhangyuncai on 2019/6/27.
 * 语言管理
 * 用法
 * LocalManageUtil.setAppLanguageSelect(isEnglish);
LocalManageUtil.getInstance().setConfiguration();

Intent intent = new Intent(getContext(), MainActivity.class);
intent.putExtra("isStartLogin", true);
intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
getContext().startActivity(intent);
 */
object LocalManageUtil {
    private val localManageContext by lazy { UtilInit.utilContext }
    //TAG 标记
    val APP_LANGUAGE_SELECT = "APP_LANGUAGE_SELECT"
    //当前app选择的语言
    private var currentLocalInteger by Preference<Int>(APP_LANGUAGE_SELECT, 0)
    //这个值 要么1 要么2  1/2 英文/中文
    private var currentLocalType = 1
        get() {
            field = currentLocalInteger + 0
            if (field != 1 && field != 2) {
                val locale = getSysLocale()
                if (locale.language == "zh") {
                    field = 2
                } else {
                    field = 1
                }
            }
            return field
        }

    /**
     * @return en/cn
     */
    fun getAppLanguage(): String {
        if (currentLocalType == 1) {//系统默认如果不是中文,那么就设置APP为英文
            return "en"
        } else {
            return "cn"
        }
    }

    /**
     * 给每个Activity的方法attachBaseContext调用即可
     * exp:Activity.attachBaseContext(LocalManageUtil.attachBaseContext(context))
     */
    fun attachBaseContext(context: Context): Context {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return createConfigurationResources(context)
        } else {
            setConfiguration()
            return context
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun createConfigurationResources(context: Context): Context {
        val resources = context.resources
        val configuration = resources.configuration
        val locale = getLanguageLocale()
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }


    /**
     * 更新语言
     */
    fun setConfiguration() {
        //获取当前local
        val targetLocale = getLanguageLocale()
        val configuration = localManageContext.getResources().getConfiguration()
        configuration.setLocale(targetLocale)
        val resources = localManageContext.getResources()
        val dm = resources.getDisplayMetrics()
        resources.updateConfiguration(configuration, dm)//语言更换生效的代码!
    }

    /**
     * 如果不是英文、简体中文、繁体中文，默认返回简体中文
     *
     * @return
     */
    fun getLanguageLocale(): Locale {
        return getAppLanguageSelect()
    }

    fun getAppLanguageSelect(): Locale {
        if (currentLocalType == 1) {//系统默认如果不是中文,那么就设置APP为英文
            return Locale.ENGLISH
        } else {
            return Locale.CHINA
        }
    }

    /**
     * 获取系统的语言类型
     */
    fun getSysLocale(): Locale {
        val locale: Locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = LocaleList.getDefault().get(0)
        } else {
            locale = Locale.getDefault()
        }
        return locale
    }

    /**
     * @param isEnglish 0/1/2 系统默认/英文/中文
     */
    fun setAppLanguageSelect(isEnglish: Boolean) {
        currentLocalInteger = if (isEnglish) 1 else 2
    }
}