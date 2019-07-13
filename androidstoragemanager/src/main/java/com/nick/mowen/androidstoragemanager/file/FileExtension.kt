package com.nick.mowen.androidstoragemanager.file

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.contentValuesOf
import java.io.File
import java.io.FileOutputStream

/**
 * Saves image to path
 * NOTE: Images can only be saved to DCIM/ and Pictures/ in Android Q
 *
 * @param data [ByteArray] to save to file location as image
 * @param displayName internal name to use for image
 * @param mimeType of image (should start with image)
 * @param relativePath where to save image
 */
fun Context.saveImage(data: ByteArray, displayName: String = "IMAGE_${System.currentTimeMillis()}.png", mimeType: String = "image/*", relativePath: String = "DCIM/Default"): Uri {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val uri = contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValuesOf(
                MediaStore.MediaColumns.DISPLAY_NAME to displayName,
                MediaStore.MediaColumns.MIME_TYPE to mimeType,
                MediaStore.MediaColumns.RELATIVE_PATH to relativePath
            )
        ) ?: return Uri.EMPTY
        contentResolver.openOutputStream(uri).let {
            if (it != null) {
                it.write(data)
                it.flush()
                it.close()
            }
        }
        return uri
    } else {
        @Suppress("DEPRECATION") val output = File(Environment.getExternalStorageDirectory(), relativePath)
        output.mkdir()
        val file = File(output, displayName)
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(data)
        fileOutputStream.flush()
        fileOutputStream.close()
        @Suppress("DEPRECATION")
        contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValuesOf(
                MediaStore.MediaColumns.DISPLAY_NAME to displayName,
                MediaStore.MediaColumns.DATA to file.absolutePath,
                MediaStore.MediaColumns.MIME_TYPE to mimeType
            )
        )
        return Uri.fromFile(file)
    }
}

/**
 * Saves image to path
 * NOTE: Images can only be saved to DCIM/ and Movies/ in Android Q
 *
 * @param data [ByteArray] to save to file location as video
 * @param displayName internal name to use for video
 * @param mimeType of video (should start with video)
 * @param relativePath where to save video
 */
fun Context.saveVideo(
    data: ByteArray,
    displayName: String = "Video_${System.currentTimeMillis()}.png",
    mimeType: String = "video/mp4",
    relativePath: String = "DCIM/Default"
): Uri {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val uri = contentResolver.insert(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValuesOf(
                MediaStore.MediaColumns.DISPLAY_NAME to displayName,
                MediaStore.MediaColumns.MIME_TYPE to mimeType,
                MediaStore.MediaColumns.RELATIVE_PATH to relativePath
            )
        ) ?: return Uri.EMPTY
        contentResolver.openOutputStream(uri).let {
            if (it != null) {
                it.write(data)
                it.flush()
                it.close()
            }
        }
        return uri
    } else {
        @Suppress("DEPRECATION") val output = File(Environment.getExternalStorageDirectory(), relativePath)
        output.mkdir()
        val file = File(output, displayName)
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(data)
        fileOutputStream.flush()
        fileOutputStream.close()
        @Suppress("DEPRECATION")
        contentResolver.insert(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValuesOf(
                MediaStore.MediaColumns.DISPLAY_NAME to displayName,
                MediaStore.MediaColumns.DATA to file.absolutePath,
                MediaStore.MediaColumns.MIME_TYPE to mimeType
            )
        )
        return Uri.fromFile(file)
    }
}

/**
 * Saves image to path
 * NOTE: Images can only be saved to DCIM/ and Music/ in Android Q
 *
 * @param data [ByteArray] to save to file location as audio
 * @param displayName internal name to use for audio
 * @param mimeType of audio (should start with audio)
 * @param relativePath where to save audio
 */
fun Context.saveAudio(data: ByteArray, displayName: String = "AUDIO_${System.currentTimeMillis()}.png", mimeType: String = "audio/*", relativePath: String = "Music/Default"): Uri {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val uri = contentResolver.insert(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, contentValuesOf(
                MediaStore.MediaColumns.DISPLAY_NAME to displayName,
                MediaStore.MediaColumns.MIME_TYPE to mimeType,
                MediaStore.MediaColumns.RELATIVE_PATH to relativePath
            )
        ) ?: return Uri.EMPTY
        contentResolver.openOutputStream(uri).let {
            if (it != null) {
                it.write(data)
                it.flush()
                it.close()
            }
        }
        return uri
    } else {
        @Suppress("DEPRECATION") val output = File(Environment.getExternalStorageDirectory(), relativePath)
        output.mkdir()
        val file = File(output, displayName)
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(data)
        fileOutputStream.flush()
        fileOutputStream.close()
        @Suppress("DEPRECATION")
        contentResolver.insert(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, contentValuesOf(
                MediaStore.MediaColumns.DISPLAY_NAME to displayName,
                MediaStore.MediaColumns.DATA to file.absolutePath,
                MediaStore.MediaColumns.MIME_TYPE to mimeType
            )
        )
        return Uri.fromFile(file)
    }
}