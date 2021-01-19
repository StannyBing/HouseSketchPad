package com.stanny.housesketchpad

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.frame.zxmvp.baseapp.BaseApplication
import com.lt.component_library.app.ConstStrings
import com.zx.zxutils.ZXApp
import com.zx.zxutils.util.ZXSystemUtil

class MyApplication : BaseApplication() {

    override fun onCreate() {
        ZXApp.init(this, true)
        super.onCreate()
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
        reinit()
        //初始化
        ConstStrings.LOCAL_PATH = ZXSystemUtil.getSDCardPath()
        ConstStrings.INI_PATH = filesDir.path
    }
    fun reinit() {
        initAppDelegate(this)
    }
}