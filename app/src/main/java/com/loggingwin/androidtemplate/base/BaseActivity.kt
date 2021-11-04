package com.loggingwin.androidtemplate.base

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.loggingwin.androidtemplate.App.Companion.TAG
import com.loggingwin.androidtemplate.R
import com.loggingwin.androidtemplate.util.Direction
import com.loggingwin.androidtemplate.util.ProgressDialog

open class BaseActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null    // loading dialog

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // only portrait mode able
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }


    fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    fun showDialog(
        title: String? = null,
        message: String? = null,
        cancelable: Boolean,
        hasPos: Boolean = false,
        hasNeg: Boolean = false,
        posClick: (() -> Unit?)? = null, // function when positive clicked
        negClick: (() -> Unit?)? = null // function when negative clicked
    ) {
        AlertDialog.Builder(this).apply {
            title?.let { setTitle(it) }
            message?.let { setMessage(it) }
            setCancelable(cancelable)

            if (hasPos || posClick != null) setPositiveButton(R.string.confirm) { dialog, which ->
                if (posClick != null) posClick()
            }

            if (hasNeg || negClick != null) setNegativeButton(R.string.cancel) { dialog, which ->
                if (negClick == null) dialog.dismiss()
                else negClick()
            }

            show()
        }
    }

    fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.Builder(this)
                .build()
        }
        progressDialog!!.show()
    }

    open fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    //    activity transition
    fun transActivity(direction: Direction, doFinish: Boolean) { // transition direction & whether current activity finish or not
        Log.d(TAG, "transitActivity: direction($direction) doFinish($doFinish)")
        if (doFinish) finish() // finish current activity

        when (direction) {
            Direction.NONE -> overridePendingTransition(0, 0) // no transition
            Direction.BOTH_SLIDE_TO_LEFT -> overridePendingTransition( // both slide to left
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            Direction.BOTH_SLIDE_TO_RIGHT -> overridePendingTransition( // both slide to right
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            Direction.ENTER_SLIDE_TO_LEFT -> overridePendingTransition( // only new view slide in from right to left
                R.anim.slide_in_right,
                R.anim.hold
            )
            Direction.EXIT_SLIDE_TO_RIGHT -> overridePendingTransition( // only current view slide out to right
                R.anim.hold,
                R.anim.slide_out_right
            )
        }
    }

    fun hideToolbar() {
        supportActionBar?.hide()
    }

    fun setToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setToolbar(title: String = "", subtitle: String = "", hasHome: Boolean = false) {
        supportActionBar?.apply {
            if (hasHome) setToolbar()
            if (title.isNotBlank()) this.title = title
            if (subtitle.isNotBlank()) this.subtitle = subtitle
        }
    }

    fun setToolbar(icon: Int) {
        supportActionBar?.apply {
            setToolbar()
            setHomeAsUpIndicator(icon)
        }
    }

    fun setToolbar(title: String = "", subtitle: String = "", icon: Int) {
        supportActionBar?.apply {
            setToolbar()
            setHomeAsUpIndicator(icon)
            if (title.isNotBlank()) this.title = title
            if (subtitle.isNotBlank()) this.subtitle = subtitle
        }
    }

    fun setToolbar(toolbar: androidx.appcompat.widget.Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    // return listener that click view when 'done' in keyboard clicked
    fun getEditorActionListener(view: View): TextView.OnEditorActionListener =
        TextView.OnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                view.callOnClick()
            }
            false
        }

    override fun onDestroy() {
        super.onDestroy()
        hideProgressDialog()
    }
}