package com.zhang.jitdemo;

import com.wushiyi.util.StringUtil;

/**
 * Created by zhangyuncai on 2019/6/26.
 */
public class TestMain {
    public static void main(String[] args) {
//        String s = StringUtil.twolastDF(1.2345);
        String s = StringUtil.INSTANCE.twolastDF(1.2345);
//        CommonExtendUtilKt.fffBug(s);
    }
}
