package com.loggingwin.androidtemplate.util
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * sample code:
 *
 * if (!Permission().checkCameraPermission(this))
 *       Permission().requestCameraPermission(this)
 *
 */

class Permission {
    val cameraCode = 401
    val readCode = 402
    private val cameraAndReadCode = 403
    private val readAndWriteCode = 404


    /* check permission */

    fun checkCameraPermission(context: Context): Boolean {
        val cameraPermission =
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

        return cameraPermission == PackageManager.PERMISSION_GRANTED
    }

    fun checkReadPermission(context: Context): Boolean {
        val readPermission =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )

        return readPermission == PackageManager.PERMISSION_GRANTED
    }


    fun checkWritePermission(context: Context): Boolean {
        val writePermission =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

        return writePermission == PackageManager.PERMISSION_GRANTED
    }


    fun checkCamaraAndReadPermission(context: Context): Boolean =
        checkReadPermission(context) && checkCameraPermission(context)

    fun checkReadAndWritePermission(context: Context): Boolean =
        checkReadPermission(context) && checkWritePermission(context)


    /* request permission */

    fun requestCameraPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.CAMERA),
            cameraCode
        )
    }

    fun requestReadPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            readCode
        )
    }

    fun requestCameraAndReadPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE),
            cameraAndReadCode
        )
    }

    fun requestReadAndWritePermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            readAndWriteCode
        )
    }
}