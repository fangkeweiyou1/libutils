package com.zhang.jitdemo;

import com.wushiyi.util.URLEncoderUtil;

import static java.lang.System.out;

/**
 * Created by zhangyuncai on 2019/6/26.
 */
public class TestMain {
    public static void main(String[] args) {
        String urlEncoded = URLEncoderUtil.INSTANCE.toURLEncoded("我爱你");
        String urlDecoder = URLEncoderUtil.INSTANCE.toURLDecoder(urlEncoded);
        out.println("-----------------<<<>>>--------------------urlEncoded:" + urlEncoded);
        out.println("-----------------<<<>>>--------------------urlDecoder:" + urlDecoder);
        out.println("-----------------<<<>>>--------------------1/3:" + (1/3));
        out.println("-----------------<<<>>>--------------------1/3:" + (1/3f));
    }
}
