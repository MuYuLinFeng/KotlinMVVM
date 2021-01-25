package com.kotlinmvvm

import android.app.Application
import android.os.Handler
import android.os.Looper

class App : Application() {
    companion object {
        val handler = Handler(Looper.getMainLooper())
    }
}