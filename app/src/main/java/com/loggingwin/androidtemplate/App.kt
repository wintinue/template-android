package com.loggingwin.androidtemplate

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.GsonBuilder
import com.loggingwin.androidtemplate.base.BaseActivity
import com.loggingwin.androidtemplate.network.XAccessTokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


class App : Application() {

    companion object { // variables and functions as static
        // server address
        val BASE_URL = "http://12.34.56.78/"

        val TAG = "@@TAG@@"

        // Strings used for SharedPreferences
        val SP_TAG = "your shared preferences tag"
        val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"

        // SharedPreferences
        lateinit var sSharedPreferences: SharedPreferences

        // activity array
        val activities = ArrayList<BaseActivity>()

        // Retrofit
        lateinit var retrofit: Retrofit

        var gson = GsonBuilder().setLenient().create()

        @JvmName("getRetrofit1")
        fun getRetrofit(): Retrofit {

            if (!this::retrofit.isInitialized) {
                val client = OkHttpClient.Builder()
                    .readTimeout(50000, TimeUnit.MILLISECONDS) // 5000
                    .connectTimeout(50000, TimeUnit.MILLISECONDS) // 5000
                    .addNetworkInterceptor(XAccessTokenInterceptor()) // put JWT into header automatically
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit
        }


        fun finishActivities() {
            for (activity in activities) activity.finish()

            activities.clear()
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: App.class")

        sSharedPreferences = applicationContext.getSharedPreferences(SP_TAG, Context.MODE_PRIVATE)
    }
}