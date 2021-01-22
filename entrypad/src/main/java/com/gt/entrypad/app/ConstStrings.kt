package com.lt.component_library.app

/**
 * Created by Xiangb on 2019/2/26.
 * 功能：
 */
object ConstStrings {
    var INI_PATH = ""
    val APPNAME = "houseSketch"
    var LOCAL_PATH: String? = null
    fun getLocalPath(): String {
        return "$LOCAL_PATH$APPNAME/"
    }

}