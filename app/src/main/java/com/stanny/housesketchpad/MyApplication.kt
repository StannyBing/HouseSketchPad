package com.stanny.housesketchpad

import android.app.Application
import com.zx.zxutils.ZXApp

class MyApplication : Application() {

    override fun onCreate() {
        ZXApp.init(this, true)
        super.onCreate()
    }

}