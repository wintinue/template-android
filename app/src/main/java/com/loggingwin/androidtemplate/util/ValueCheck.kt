package com.loggingwin.androidtemplate.util

import java.util.regex.Pattern

class ValueCheck {
    fun isEmail(email: String): Boolean {
        if (email.isBlank())
            return false

        val regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$" // email pattern
        val pE = Pattern.compile(regex) // compile email pattern
        val mE = pE.matcher(email) // check is email
        return mE.matches()
    }

    fun getUrl(uri: String): String {
        val regex = "^[\\w]+://[\\S]+$" /// url regex
        val pE = Pattern.compile(regex) // compile url pattern
        val mE = pE.matcher(uri) // check is url

        // if is not url, make it url
        return if (mE.matches()) uri else "http://$uri"
    }
}