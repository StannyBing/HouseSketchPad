package com.lt.component_library.app

/**
 * Created by Xiangb on 2019/2/26.
 * 功能：
 */
object ConstStrings {
    var INI_PATH = ""
    val APPNAME = "houseSketch"
    var LOCAL_PATH: String? = null

    var normalCenterLongtitude = 106.37606842510831
    var normalCenterLatitude = 29.423709493704152

    fun getDatabasePath(): String {
        return "$LOCAL_PATH$APPNAME/DATABASE/"
    }

    fun getCachePath(): String {
        return "$LOCAL_PATH$APPNAME/Cache/"
    }

    fun getSnapShotPath(): String {
        return "$LOCAL_PATH$APPNAME/SnapShot/"
    }

    fun getZipPath(): String {
        return "$LOCAL_PATH$APPNAME/.zip/"
    }

    fun getOnlinePath(): String {
        return "$LOCAL_PATH$APPNAME/ONLINE/"
    }

    fun getCrashPath(): String {
        return "$LOCAL_PATH$APPNAME/CRASH/"
    }

    fun getApkPath(): String {
        return "$LOCAL_PATH$APPNAME/APK/"
    }

    fun getLocalPath(): String {
        return "$LOCAL_PATH$APPNAME/"
    }

}