package com.loggingwin.androidtemplate.util

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.loggingwin.androidtemplate.App.Companion.TAG
import com.loggingwin.androidtemplate.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class ImageTask {

    @SuppressLint("SimpleDateFormat")
    private fun getTimeStamp(): String = SimpleDateFormat("yyyyMMddHHmmss").format(Date())

    @SuppressLint("SimpleDateFormat")
    fun createImageFile(context: Context): File {
        val fileName = "IMAGE_${getTimeStamp()}" // image file name
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDir) // create and return image file
    }

    /* return full path of simple uri when select photo from gallery */
    fun getFullPathFromUri(context: Context, fileUri: Uri): String? {
        var fullPath: String? = null
        val column = "_data"
        var cursor: Cursor? = context.contentResolver.query(fileUri, null, null, null, null)
        if (cursor != null) {
            cursor.moveToFirst()
            var documentId: String = cursor.getString(0)
            documentId = documentId.substring(documentId.lastIndexOf(":") + 1)
            cursor.close()
            val projection = arrayOf(column)
            try {
                cursor = context.contentResolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    MediaStore.Images.Media._ID + " = ? ",
                    arrayOf(documentId),
                    null
                )
                if (cursor != null) {
                    cursor.moveToFirst()
                    fullPath = cursor.getString(cursor.getColumnIndexOrThrow(column))
                }
            } finally {
                cursor.close()
            }
        }
        return fullPath
    }


    @SuppressLint("SimpleDateFormat")
    fun downloadImage(context: Context, url: String): Long {
        Log.d(TAG, "downloadImage: url: $url")
        val fileName =
            "/${context.getString(R.string.app_name)}/${getTimeStamp()}.jpg" // image file name

        val req = DownloadManager.Request(Uri.parse(url))

        req.setTitle(fileName) // 제목
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN) // hide notification about download
            .setMimeType("image/*")
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_PICTURES,
                fileName
            ) // shown name after download finish

        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        return manager.enqueue(req)
    }
}