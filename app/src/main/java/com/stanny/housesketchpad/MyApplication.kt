package com.stanny.housesketchpad

import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.baidu.ocr.sdk.OCR
import com.baidu.ocr.sdk.OnResultListener
import com.baidu.ocr.sdk.exception.OCRError
import com.baidu.ocr.sdk.model.AccessToken
import com.frame.zxmvp.baseapp.BaseApplication
import com.lt.component_library.app.ConstStrings
import com.zx.zxutils.ZXApp
import com.zx.zxutils.util.ZXSystemUtil
import com.zx.zxutils.util.ZXToastUtil.showToast

class MyApplication : BaseApplication() {
    private var hasGotToken = false
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