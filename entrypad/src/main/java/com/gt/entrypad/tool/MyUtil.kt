package com.gt.entrypad.tool

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

object MyUtil{
    /**
     * 将图片保存到本地时进行压缩, 即将图片从Bitmap形式变为File形式时进行压缩,
     * 特点是: File形式的图片确实被压缩了, 但是当你重新读取压缩后的file为 Bitmap是,它占用的内存并没有改变
     *
     * @param bitmap 位图
     * @param file   存储的文件
     */
    fun bitmapToFile(bitmap: Bitmap, file: File) {
        val baos = ByteArrayOutputStream()
        var options = 30// 个人喜欢从30开始,
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos)
        /* while (baos.toByteArray().size / 1024 > 100) {
             baos.reset()
             options -= 10
             bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos)
         }*/
        try {
            val fos = FileOutputStream(file)
            fos.write(baos.toByteArray())
            fos.flush()
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}