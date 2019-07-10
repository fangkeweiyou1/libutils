package com.wushiyi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;


/**
 * Created by zhangyuncai on 2018/9/11.
 * 需要初始化
 */
public class SharedPreferencesUtils {
    //默认文件名称为应用名称
    private static String preferenceFileName = "";
    //权限模式
    private static final int SP_MODE = Context.MODE_PRIVATE;
    private static SharedPreferences sp;

    private static Context getContext() {
        return UtilInit.utilContext;
    }

    private static String getPreferenceFileName() {
        if (TextUtils.isEmpty(preferenceFileName)) {
            preferenceFileName = AppUtil.INSTANCE.getPackageNameLastChar();
        }
        return preferenceFileName;
    }

    public static void setPreferenceFileName(String preferenceFileName) {
        SharedPreferencesUtils.preferenceFileName = preferenceFileName;
    }

    public static void initSharedPreferences() {
        if (sp == null) {
            sp = getContext().getApplicationContext().getSharedPreferences(preferenceFileName, SP_MODE);
        }
    }

    /**
     * 保存boolean
     *
     * @param key
     * @param value
     */
    public static void saveBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * 读取boolean
     *
     * @param key
     * @return 默认为true
     */
    public static boolean loadBoolean(String key) {
        return sp.getBoolean(key, true);
    }


    /**
     * 保存string
     *
     * @param key
     * @param value
     */
    public static void saveString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }


    public static void removeString(String key) {
        sp.edit().remove(key).apply();
    }


    /**
     * 读取string
     *
     * @param key
     * @return 默认为 ""
     */
    public static String loadString(String key) {
        return sp.getString(key, "");
    }

    /**
     * 读取string
     *
     * @param key
     * @return 默认为 defValue
     */
    public static String loadString(String key,String defValue) {
        return sp.getString(key, defValue);
    }

    /**
     * 保存int
     *
     * @param key
     * @param value
     */
    public static void saveInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }


    /**
     * 读取int
     *
     * @param key
     * @return 默认为0
     */
    public static int loadInt(String key) {
        return sp.getInt(key, 0);
    }

    /**
     * 读取int
     *
     * @param key
     * @return 默认为defValue
     */
    public static int loadInt(String key,int defValue) {
        return sp.getInt(key, defValue);
    }


}
