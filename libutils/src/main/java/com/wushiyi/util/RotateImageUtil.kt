package com.wushiyi.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import java.io.File
import java.io.FileOutputStream

/**
 * Created by zhangyuncai on 2019/7/3.
 * 图片旋转
 */
object RotateImageUtil {

    /**
     * 获取图片的旋转角度，有些系统把拍照的图片旋转了，有的没有旋转
     * ############还没有经过代码验证##########
     */
    fun compressImage(srcPath: String, outFilePath: String) {
        //获取旋转角度
        val degree = readPictureDegree(srcPath)

        val DEF_MIN = 480
        val DEF_MAX = 1280

        val newOpts = BitmapFactory.Options()
        // 只读边,不读内容
        newOpts.inJustDecodeBounds = true
        //读取图片宽高信息
        BitmapFactory.decodeFile(srcPath, newOpts)
        //获取图片宽高最小值
        val picMin = Math.min(newOpts.outWidth, newOpts.outHeight)
        //获取图片宽高最大值
        val picMax = Math.max(newOpts.outWidth, newOpts.outHeight)
        //原图的比例
        val picShap = 1.0f * picMin / picMax
        //指定比例
        val defShap = 1.0f * DEF_MIN / DEF_MAX
        //初始缩放值=1
        var scale = 1f


        if (picMax < DEF_MAX) {//图片最大值<指定最大值,不用缩放

        } else if (picShap > defShap) {// 比较 方正的，等比压缩至最大值
            scale = 1.0f * DEF_MAX / picMax
        } else if (picMin > DEF_MIN) {// 较窄边压缩至最小值
            scale = 1.0f * DEF_MIN / picMin
        }
        val scaledWidth = (scale * newOpts.outWidth).toInt()//缩放宽像素值
        val scaledHeight = (scale * newOpts.outHeight).toInt()//缩放高像素值

        newOpts.inSampleSize = ((if (scale == 1f) 1 else 2) / scale).toInt()//宽高按缩放值缩放

        newOpts.inJustDecodeBounds = false//可以读取内容
        var bitmap = BitmapFactory.decodeFile(srcPath, newOpts)//按指定图片缩放信息读取图片并返回bitmap

        if (scale != 1f) {
            val matrix = Matrix()
            scale = scaledWidth.toFloat() / bitmap.width
            matrix.setScale(scale, scale)
            val bitmapNew = Bitmap.createBitmap(
                bitmap, 0, 0,
                bitmap.width, bitmap.height, matrix, false
            )
            bitmap.recycle()
            bitmap = bitmapNew
        }

        val quality: Int
        if (scaledHeight * scaledWidth > 1280 * 720) {
            quality = 30
        } else {
            quality = 80 - (scaledHeight.toFloat() * scaledWidth / (1280 * 720) * 50).toInt()
        }

        try {
            /**
             * 把图片旋转为正的方向
             */
            bitmap = rotaingImageView(degree.toFloat(), bitmap)
            bitmap.compress(
                Bitmap.CompressFormat.JPEG, quality,
                FileOutputStream(File(outFilePath))
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        bitmap.recycle()
        System.gc()
    }

    /**
     * 获取旋转角度
     */
    private fun readPictureDegree(path: String): Int {
        var degree = 0

        try {
            val exifInterface = ExifInterface(path)
            val orientation = exifInterface.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> {
                    degree = 90
                }
                ExifInterface.ORIENTATION_ROTATE_180 -> {
                    degree = 180
                }
                ExifInterface.ORIENTATION_ROTATE_270 -> {
                    degree = 270
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return degree
    }

    /**
     *旋转图片
     */
    private fun rotaingImageView(angle: Float, bitmap: Bitmap): Bitmap {
        // 旋转图片 动作
        val matrix = Matrix()
        matrix.postRotate(angle)
        // 创建新的图片
        val resizedBitmap = Bitmap.createBitmap(
            bitmap, 0, 0,
            bitmap.width, bitmap.height, matrix, true
        )
        return resizedBitmap
    }
}