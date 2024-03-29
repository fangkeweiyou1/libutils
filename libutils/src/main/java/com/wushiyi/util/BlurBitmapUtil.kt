package com.wushiyi.util

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.support.annotation.RequiresApi

/**
 * Created by zhangyuncai on 2019/6/27.
 * 高斯模糊工具类
 * // 将该资源图片转为Bitmap
Bitmap resBmp = BitmapFactory.decodeResource(getResources(), resourceId);
// 将该Bitmap高斯模糊后返回到resBlurBmp
Bitmap resBlurBmp = BlurBitmapUtil.blurBitmap(mRecyclerView.getContext(), resBmp, 15f);
// 再将resBlurBmp转为Drawable
Drawable resBlurDrawable = new BitmapDrawable(resBlurBmp);
 */
object BlurBitmapUtil {
    private val BITMAP_SCALE: Float = 0.4f

    /**
     * 模糊图片的具体方法
     *
     * @param context 上下文对象
     * @param image   需要模糊的图片
     * @param blurRadius 25f是最大模糊度,数字越大越模糊
     * @return 模糊处理后的图片
     */
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun blurBitmap(context: Context, image: Bitmap, blurRadius: Float): Bitmap {
        // 计算图片缩小后的长宽
        val width = Math.round(image.width * BITMAP_SCALE)
        val height = Math.round(image.height * BITMAP_SCALE)

        // 将缩小后的图片做为预渲染的图片
        val inputBitmap = Bitmap.createScaledBitmap(image, width, height, false)
        // 创建一张渲染后的输出图片
        val outputBitmap = Bitmap.createBitmap(inputBitmap)

        // 创建RenderScript内核对象
        val rs = RenderScript.create(context)
        // 创建一个模糊效果的RenderScript的工具对象
        val blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
        val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
        val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)

        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(blurRadius)
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn)
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut)

        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap)

        return outputBitmap
    }
}