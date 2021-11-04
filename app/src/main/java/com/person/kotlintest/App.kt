package com.person.kotlintest

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var mContext: Context

    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        LeakCanary.install(this)
    }

}