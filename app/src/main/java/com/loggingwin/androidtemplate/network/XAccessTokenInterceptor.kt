package com.loggingwin.androidtemplate.network

import com.loggingwin.androidtemplate.App
import okhttp3.Interceptor
import okhttp3.Response

class XAccessTokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val jwtToken: String? =
            App.sSharedPreferences.getString(App.X_ACCESS_TOKEN, null)

        if (jwtToken != null) {
            builder.addHeader(App.X_ACCESS_TOKEN, jwtToken)
        }
        return chain.proceed(builder.build())
    }
}