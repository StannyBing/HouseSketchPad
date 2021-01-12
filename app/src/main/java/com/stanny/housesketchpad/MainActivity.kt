package com.stanny.housesketchpad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zx.zxutils.views.ZXStatusBarCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ZXStatusBarCompat.translucent(this)
        ZXStatusBarCompat.setStatusBarLightMode(this)
    }
}
