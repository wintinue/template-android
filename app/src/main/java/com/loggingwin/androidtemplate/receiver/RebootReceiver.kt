package com.loggingwin.androidtemplate.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.loggingwin.androidtemplate.App.Companion.TAG

class RebootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "${this.javaClass.simpleName} - onReceive: ")

        /* things after reboot */
    }

}