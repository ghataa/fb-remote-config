package com.ghataa.fbremoteconfig

import android.app.Application
import android.util.Log
import io.reactivex.plugins.RxJavaPlugins

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler { throwable: Throwable? ->
            Log.d(MyApplication::class.java.canonicalName, throwable.toString())
        }
    }
}
