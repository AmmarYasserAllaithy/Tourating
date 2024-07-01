package com.ammaryasser.tourating

import android.app.Application
import android.content.Context


class App : Application() {
    init {
        application = this
    }

    companion object {
        private lateinit var application: Application

        fun context(): Context = application.applicationContext
    }
}