package com.loggingwin.androidtemplate.receiver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.util.Log
import com.loggingwin.androidtemplate.App.Companion.TAG
import com.loggingwin.androidtemplate.base.BaseActivity


class DownloadReceiver : BroadcastReceiver() {
    lateinit var activity: BaseActivity
    var queueId: Long = -1

    fun getInstance(activity: BaseActivity): DownloadReceiver {
        this.activity = activity
        return this
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "${this.javaClass.simpleName} - onReceive: ")

        val reference = intent!!.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)

        Log.d(TAG, "onReceive: queueId: $queueId / reference: $reference")
        if (queueId == reference) {
            val query = DownloadManager.Query() // include information necessary for download item inquiry
                .setFilterById(reference)
            val cursor: Cursor =
                (activity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager).query(query)
            cursor.moveToFirst()
            val columnIndex: Int = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
            val status: Int = cursor.getInt(columnIndex)
            cursor.close()

            Log.d(TAG, "onReceive: status: $status")
            when (status) {
                DownloadManager.STATUS_SUCCESSFUL -> activity.showToast("saved")
                DownloadManager.STATUS_PAUSED -> activity.showToast("download paused")
                DownloadManager.STATUS_FAILED -> activity.showToast("download canceled")
            }
        }
    }
}