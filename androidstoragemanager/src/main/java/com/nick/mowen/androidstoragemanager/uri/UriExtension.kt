package com.nick.mowen.androidstoragemanager.uri

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.graphics.decodeBitmap
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException

/**
 *
 */
private fun Uri?.getCompressedImage(context: Context): Bitmap? {
    this ?: return null

    return try {
        val stream = ByteArrayOutputStream()

        @Suppress("DEPRECATION")
        val bitmap = (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            ImageDecoder.createSource(context.contentResolver, this).decodeBitmap { _, _ -> }
        else
            MediaStore.Images.Media.getBitmap(context.contentResolver, this))

        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream)
        BitmapFactory.decodeStream(ByteArrayInputStream(stream.toByteArray()))
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

/**
 *
 */
fun Uri.getCompressedImageBytes(context: Context): ByteArray {
    return try {
        getCompressedImage(context)?.let {
            val stream = ByteArrayOutputStream()
            it.compress(Bitmap.CompressFormat.JPEG, 80, stream)
            val array = stream.toByteArray()
            it.recycle()
            array
        } ?: byteArrayOf()
    } catch (e: Exception) {
        e.printStackTrace()
        byteArrayOf()
    }
}