package com.loggingwin.androidtemplate.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.loggingwin.androidtemplate.R

class ProgressDialog(private val mContext: Context) : Dialog(mContext) {
    constructor(builder: Builder) : this(builder.context)

    class Builder(val context: Context) {
        fun build(): ProgressDialog {
            return ProgressDialog(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}

